package com.pp.base.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形节点模型
 *
 */
public class Node {

    public Node() {
        this.nodes = new ArrayList<Node>();
    }
    public Node(String nodeId,String pId) {
        this.nodeId = nodeId;
        this.pid = pId;
        this.nodes = new ArrayList<Node>();
    }
    /**
     * 生成一个节点
     * @param id
     * @param pId
     * @param text
     * @param icon
     * @param href
     */
    public Node(String id, String pId, String pName, String text, String icon, String href, String desc, String type, String otherType) {
        super();
        this.id = id;
        this.pid = pId;
        this.pName = pName;
        this.text = text;
        this.icon = icon;
        this.href = href;
        this.desc = desc;
        this.type = type;
        this.otherType = otherType;
        this.nodes = new ArrayList<Node>();
    }

    private String id;         // 数据库中保存的数据Id
    private String nodeId;    //树的节点Id，区别于数据库中保存的数据Id。
    private String pid;
    private String pName;
    private String text;   //节点名称
    private String icon;
    private String href;   //点击节点触发的链接
    private List<Node> nodes;    //子节点，可以用递归的方法读取
    private String desc;//  小区时'地址编码'
    private String type;//
    private String typeName;
    private String otherType;   // 小区时-->building_type
    private String otherTypeName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }
    public void setHref(String href) {
        this.href = href;
    }

    public List<Node> getNodes() {
        return nodes;
    }
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOtherType() {
        return otherType;
    }

    public void setOtherType(String otherType) {
        this.otherType = otherType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getOtherTypeName() {
        return otherTypeName;
    }

    public void setOtherTypeName(String otherTypeName) {
        this.otherTypeName = otherTypeName;
    }


    public static List<Node> buildTree(List<Node> list,String parentId){
        List<Node> menus=new ArrayList<Node>();
        for (Node menu : list) {
            String menuId = menu.getId();
            String pid = menu.getPid();
            if (parentId.equals(pid)) {
                List<Node> menuLists = buildTree(list, menuId);

                menu.setNodes(menuLists);
                menus.add(menu);
            }
        }

        return menus;
    }



    /**
     * 生成一颗多叉树，根节点为root
     * @param Nodes 生成多叉树的节点集合
     * @return NodeTree
     */
    public Node createTree(List<Node> Nodes) {
        if (Nodes == null || Nodes.size() < 0)
            return null;
        Node root = new Node("root","0");
        // 将所有节点添加到多叉树中
        for (Node node : Nodes) {
            if (StringUtils.isBlank(node.getPid()) || node.getPid().equals("0") || node.getPid().equals("root")) {
                // 向根添加一个节点
                root.getNodes().add(node);
            } else {
                addChild(root, node);
            }
        }
        return root;
    }

    /**
     * 向指定多叉树节点添加子节点
     * @param Node 多叉树节点
     * @param child 节点
     */
    public void addChild(Node Node, Node child) {
        for (Node item : Node.getNodes()) {
            if (item.getNodeId().equals(child.getPid())) {
                // 找到对应的父亲
                item.getNodes().add(child);
                break;
            } else {
//                if (item.getNodes() != null && item.getNodes().size() > 0) {
                    addChild(item, child);
//                }
            }
        }
    }

    /**
     * 遍历多叉树
     * @param Node 多叉树节点
     * @return
     */
    public String iteratorTree(Node Node) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("\n");
        if (Node != null) {
            for (Node index : Node.getNodes()) {
                buffer.append(index.getNodeId() + ",");
                if (index.getNodes() != null && index.getNodes().size() > 0) {
                    buffer.append(iteratorTree(index));
                }
            }
        }
        buffer.append("\n");
        return buffer.toString();
    }


    /**
     * 遍历多叉树
     * @param Node 多叉树节点
     * @return
     */
    List<Node> node = new ArrayList<Node>();
    public Node zxPraseTree(Node Node) {
        if (Node != null) {
            for (Node index : Node.getNodes()) {
                node.add(index);
                if (index.getNodes() != null && index.getNodes().size() > 0) {
                    node.add(zxPraseTree(index));
                }
            }
        }
        //buffer.append("\n");
        Node n = new Node();
        n.setNodes(node);
        return n;
    }

    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node("系统管理", "0"));
        nodes.add(new Node("角色管理", "系统管理"));
        nodes.add(new Node("资源管理", "系统管理"));
        nodes.add(new Node("用户管理", "系统管理"));
        nodes.add(new Node("添加用户", "用户管理"));
        nodes.add(new Node("修改用户", "用户管理"));

        Node tree = new Node();
        Node mt = tree.createTree(nodes);
        System.out.println(tree.iteratorTree(mt));
        Node n = tree.zxPraseTree(mt);
        List<Node> list = n.getNodes();
        for(Node node : list) {
            System.out.println(node.getNodeId());
        }
    }


}