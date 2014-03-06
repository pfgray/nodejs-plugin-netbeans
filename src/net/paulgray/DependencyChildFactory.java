/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.paulgray.nodes.EventChildFactory;
import org.json.simple.JSONObject;
import org.openide.filesystems.FileObject;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author pgray
 */
public class DependencyChildFactory extends ChildFactory<Dependency> {

    private JSONObject dependencies;
    private FileObject nodeModulesFolder;
    private Map<String, Dependency> deps;

    public DependencyChildFactory(JSONObject dependencies, FileObject nodeModulesFolder) {
        this.dependencies = dependencies;
        this.nodeModulesFolder = nodeModulesFolder;
    }

    @Override
    protected boolean createKeys(List<Dependency> list) {

        for (Object s : dependencies.keySet()) {
            String name = (String) s;
            String version = (String) dependencies.get(name);
            deps.put(name, new Dependency(name, version, new LinkedList<Node>(), Boolean.FALSE));
        }

        for (FileObject depDirectory : nodeModulesFolder.getChildren()) {
            Dependency dependency = deps.get(depDirectory.getName());
            dependency.setInstalled(Boolean.TRUE);
            deps.put(dependency.getName(), dependency);
        }
        list.addAll(deps.values());
        return true;
    }

    @Override
    protected Node createNodeForKey(Dependency key) {
        Node result = new AbstractNode(
                Children.create(new EventChildFactory(), true),
                Lookups.singleton(key));
        result.setDisplayName(key.toString());
        return result;
    }

}
