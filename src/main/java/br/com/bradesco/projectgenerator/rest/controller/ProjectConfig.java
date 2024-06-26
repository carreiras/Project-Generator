package br.com.bradesco.projectgenerator.rest.controller;

import java.util.List;

public class ProjectConfig {

    private String projectName;
    private String groupId;
    private String artifactId;
    private String version;
    private String costCenter;
    private String valueStream;
    private String namespace;
    private String clustersName;

    private List<String> excludes;

    // getters and setters

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getExcludes() {
        return excludes;
    }


    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getValueStream() {
        return valueStream;
    }

    public void setValueStream(String valueStream) {
        this.valueStream = valueStream;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getClustersName() {
        return clustersName;
    }

    public void setClustersName(String clustersName) {
        this.clustersName = clustersName;
    }

    public void setExcludes(List<String> excludes) {
        this.excludes = excludes;
    }
}
