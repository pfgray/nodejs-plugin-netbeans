/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.nodes;

import java.util.Comparator;
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
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author pgray
 */
@NodeFactory.Registration(projectType = "org-customer-project", position = 10)
public class NodeNodeFactory implements NodeFactory {
    
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
            
            Children.SortedArray array = new Children.SortedArray();
            
            array.setComparator(new Comparator<Node>() {
                
                @Override
                public int compare(Node node1, Node node2) {
                    return 1;
                }
            });
            
            
            for (FileObject file : project.getProjectDirectory().getChildren()) {
                if (file.getName().equals("node_modules") && file.isFolder()) {
                    
                } else if (file.getName().startsWith(".")) {
                    
                } else {
                    try {
                        result.add(DataObject.find(file).getNodeDelegate());
                    } catch (DataObjectNotFoundException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            }
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
        
    }
    
}
