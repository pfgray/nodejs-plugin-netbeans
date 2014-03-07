/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.nodes;

import java.util.LinkedList;
import java.util.List;
import org.openide.filesystems.FileObject;

/**
 *
 * @author pgray
 */
public abstract class FileObjectFilter {

    public static FileObjectFilter FOLDERS = new FileObjectFilter() {
        @Override
        public List<FileObject> filter(List<FileObject> fileObjects) {
            List<FileObject> toReturn = new LinkedList<FileObject>();
            for (FileObject fo : fileObjects) {
                if (fo.isFolder()) {
                    toReturn.add(fo);
                }
            }
            return toReturn;
        }
    };

    public static FileObjectFilter FILES = new FileObjectFilter() {
        @Override
        public List<FileObject> filter(List<FileObject> fileObjects) {
            List<FileObject> toReturn = new LinkedList<FileObject>();
            for (FileObject fo : fileObjects) {
                if (!fo.isFolder()) {
                    toReturn.add(fo);
                }
            }
            return toReturn;
        }
    };

    public abstract List<FileObject> filter(List<FileObject> fileObjects);
}
