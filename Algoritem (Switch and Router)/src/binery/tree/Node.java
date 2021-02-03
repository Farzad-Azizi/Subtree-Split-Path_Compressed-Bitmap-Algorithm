package binery.tree;

/**
 *
 * @author Farzad
 */

import java.util.ArrayList;

public class Node{
    
        Node parent;
        private ArrayList child;
        private boolean isOutput;
        private String outputPort;
        
        // Level-Compression feature properties
        int stride;
        
        // Path-Compression feature properties
        int skip;  
        String segment;
        
        // Subtree Split feature 
        int prefixes;

        public Node(){
            child = new ArrayList();
            parent = null;
            isOutput = false;
            outputPort = "";
            stride = 1;
            skip = 0;
            segment = "";
            prefixes = 0;
        }
        
        public void addChild(Node n){
            child.add(n);
        }

        public void addChild(Node n, int index){
            int count = getBranchesCount();
            if(index > count + 1){
                for(int i=count;i<index;i++)    addChild(null);
            }
            child.add(index,n);
        }
        
        public void setChild(Node n, int index){
            if(index < getBranchesCount()){
                child.remove(index);
                child.add(index,n);
            }
            else addChild(n,index);
        }
        
        public void removeChild(int idx){
            if(idx > getBranchesCount())    System.out.println("Error: Trying to remove an out of bands index[removeChild(index)][Class:Node]");
            else    child.remove(idx);
        }
        
        public Node getChild(int idx){
            if(idx > getBranchesCount())    System.out.println("Error: Trying to get an out of bands index[getChild(index)][Class:Node]");
            return (Node)child.get(idx);
        }
        
        // Detects leaf node
        public boolean isLeaf(){
            boolean leaf = false;
            if(getChildsCount() == 0)   leaf = true;
            return leaf;
        }
        
        public void setOutput(String port){
            isOutput = true;
            outputPort = port;
        }
        
        public String getOutputPort(){
            return outputPort;
        }
        
        public boolean isOutput(){
            return isOutput;
        }

        public int getBranchesCount(){
            return child.size();
        }
        
        public int getChildsCount(){
            int childs = 0;
            for(int i=0;i<getBranchesCount();i++){
                if(getChild(i) != null) childs++;
            }
            return childs;
        }
  
        public int getIndex(){
            int index = -1;
            if(this.parent != null){
                for(index=0;index<getBranchesCount();index++){
                    if(this.parent.getChild(index) == null) continue;      // -----> ADDED
                    if(this.parent.getChild(index).equals(this))   break;
                }
            }
            return index;
        }
        
        // Returns 'null' if the node is a leaf
        public Node getMostLeftChild(){
            Node n = null;
            if(!this.isLeaf()){
                int idx = 0;
                while(this.getChild(idx) == null)   idx++;
                n = this.getChild(idx);
            }
            else    System.out.println("CAUTION: Attempting to getChild() from a leaf node! it may cause NullPointer Exception. [getMostLeftChild() in Node class]");
            return n;
        }
        
        // Returns 'null' if the node is a leaf
        public Node getMostRightChild(){
            Node n = null;
            if(!this.isLeaf()){
                int idx = this.getBranchesCount()-1;
                while(this.getChild(idx) == null)   idx--;
                n = this.getChild(idx);
            }
            else    System.out.println("CAUTION: Attempting to getChild() from a leaf node! it may cause NullPointer Exception. [getMostRightChild() in Node class]");
            return n;
        }
    }
