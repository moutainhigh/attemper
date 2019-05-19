package com.github.attemper.core.service.project;

import com.github.attemper.common.exception.RTException;
import com.github.attemper.common.param.dispatch.project.*;
import com.github.attemper.common.result.dispatch.project.Project;
import com.github.attemper.common.result.dispatch.project.ProjectInfo;
import com.github.attemper.core.dao.mapper.project.ProjectMapper;
import com.github.attemper.sys.holder.TenantHolder;
import com.github.attemper.sys.service.BaseServiceAdapter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author ldang
 */
@Service
@Transactional
public class ProjectService extends BaseServiceAdapter {

    @Autowired
	private ProjectMapper mapper;

    public List<Project> getAll() {
        Map<String, Object> paramMap = injectTenantIdToMap(null);
        return getAll(paramMap);
    }

    public List<Project> getAll(Map<String, Object> paramMap) {
        List<Project> sourceList = mapper.getAll(paramMap);
        List<Project> targetList = new ArrayList<>(sourceList.size());
        Project rootProject = findRootProject(sourceList);
        targetList.add(rootProject);
        computeTreeList(sourceList, targetList, rootProject);
        return targetList;
    }

    public Project save(ProjectSaveParam param) {
        Project project = toProject(param);
        Date now = new Date();
        project.setCreateTime(now);
        project.setUpdateTime(now);
        mapper.save(injectTenantIdToMap(project));
        return project;
    }

    public Void remove(ProjectRemoveParam param) {
        Map<String, Object> paramMap = injectTenantIdToMap(param);
        mapper.delete(paramMap);
        return null;
    }

    private void computeTreeList(List<Project> sourceList, List<Project> targetList, Project cellProject) {
	    Iterator<Project> it = sourceList.iterator();
	    boolean find = false;
	    while(it.hasNext()){
	        Project current = it.next();
	        if(StringUtils.equals(current.getParentProjectName(), cellProject.getProjectName())){
                targetList.add(current);
                find = true;
                computeTreeList(sourceList, targetList, current);
            }
        }
        if(!find){
	        return;
        }
    }

    private Project findRootProject(List<Project> sourceList) {
	    List<Project> projects =
                sourceList.stream().filter(project -> project.getParentProjectName() == null).collect(Collectors.toList());
        if (projects.size() == 0) {
            ProjectSaveParam root = ProjectSaveParam.builder()
                    .projectName("root")
                    .displayName(TenantHolder.get().getDisplayName())
                    .position(1)
                    .build();
            Project project = save(root);
            projects.add(project);
        } else if (projects.size() > 1) {
            throw new RTException(6570, String.valueOf(projects.size()));
        }
        return projects.get(0);
    }

    private Project toProject(ProjectSaveParam param) {
        return Project.builder()
                .projectName(param.getProjectName())
                .parentProjectName(param.getParentProjectName())
                .displayName(param.getDisplayName())
                .contextPath(param.getContextPath())
                .position(param.getPosition())
                .build();
    }


    public Void saveInfo(ProjectInfoSaveParam param) {
        Map<String, Object> paramMap = injectTenantIdToMap(param);
        mapper.saveInfo(paramMap);
        return null;
    }

    public Void removeInfo(ProjectInfoRemoveParam param) {
        Map<String, Object> paramMap = injectTenantIdToMap(param);
        mapper.deleteInfo(paramMap);
        return null;
    }

    public List<ProjectInfo> listInfo(ProjectGetParam param) {
        Map<String, Object> paramMap = injectTenantIdToMap(param);
        return listInfo(paramMap);
    }

    public List<ProjectInfo> listInfo(Map<String, Object> paramMap) {
        return mapper.listInfo(paramMap);
    }
}