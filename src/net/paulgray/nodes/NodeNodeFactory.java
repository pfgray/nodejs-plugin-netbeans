/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.nodes;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.event.ChangeListener;
import net.paulgray.NodeJsProject;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ui.support.NodeFactory;
import org.netbeans.spi.project.ui.support.NodeList;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;

/**
 *
 * @author pgray
 */
@NodeFactory.Registration(projectType = "org-customer-project", position = 10)
public class NodeNodeFactory implements NodeFactory {

    private static Boolean SHOW_DOT_FILES = false;

    @Override
    public NodeList<?> createNodes(Project project) {
        NodeJsProject p = project.getLookup().lookup(NodeJsProject.class);
        assert p != null;
        return new NodeNodeList(p);
    }

    private class NodeNodeList implements NodeList<Node> {

        NodeJsProject project;

        public NodeNodeList(NodeJsProject project) {
            this.project = project;
        }

        @Override
        public List<Node> keys() {
            List<Node> result = new LinkedList<Node>();
            List<FileObject> children = Arrays.asList(project.getProjectDirectory().getChildren());

            result.addAll(this.getNodes(children, FileObjectFilter.FOLDERS));
            result.addAll(this.getNodes(children, FileObjectFilter.FILES));
            //new AbstractNode(Children.create(new EventChildFactory(), true), Lookups.singleton(key));

            return result;
        }

        @Override
        public Node node(Node node) {
            return new FilterNode(node);
        }

        @Override
        public void addNotify() {
        }

        @Override
        public void removeNotify() {
        }

        @Override
        public void addChangeListener(ChangeListener cl) {
        }

        @Override
        public void removeChangeListener(ChangeListener cl) {
        }

        private List<Node> getNodes(List<FileObject> objects, FileObjectFilter filter) {
            List<Node> nodes = new LinkedList<Node>();
            for (FileObject fo : filter.filter(objects)) {
                if (!fo.getName().equals("node_modules")
                       && (!fo.getName().startsWith(".") || SHOW_DOT_FILES)) {
                    try {
                        nodes.add(DataObject.find(fo).getNodeDelegate());
                    } catch (DataObjectNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            return nodes;
        }

    }

}
