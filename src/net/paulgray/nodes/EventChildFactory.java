/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.nodes;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import net.paulgray.Dependency;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;

/**
 *
 * @author pgray
 */
public class EventChildFactory extends ChildFactory<Dependency> {

    @Override
    protected boolean createKeys(List list) {
        Dependency[] objs = new Dependency[5];
        for (int i = 0; i < objs.length; i++) {
            objs[i] = new Dependency("name","version", new LinkedList<Node>(), false);
        }
        list.addAll(Arrays.asList(objs));
        return true;
    }
    
}
