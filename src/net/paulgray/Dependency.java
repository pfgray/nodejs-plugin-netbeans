/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray;

import java.util.List;
import org.openide.nodes.Node;

/**
 *
 * @author pgray
 */
public class Dependency {
    
    private String name;
    private String version;
    private List<Node> children;
    private Boolean installed;

    public Dependency(String name, String version, List<Node> children, Boolean installed) {
        this.name = name;
        this.version = version;
        this.children = children;
        this.installed = installed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Boolean isInstalled() {
        return installed;
    }

    public void setInstalled(Boolean installed) {
        this.installed = installed;
    }
    
}
