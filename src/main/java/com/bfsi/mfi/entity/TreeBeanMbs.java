package com.bfsi.mfi.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.bfsi.mfi.vo.FunctionMbsVO;

/**
 * Tree Structure Design and assign Functions to it.
 * 
 * @author Jyoti Ranjan
 * 
 */

public class TreeBeanMbs implements Serializable {
	private static final long serialVersionUID = -9125123542445061566L;
	private TreeNode root;
	private TreeNode[] selectedNodes;
	private List<FunctionMbsVO> functionsList;
	private Map<TreeNode, FunctionMbsVO> nodeToFunctionMap = new HashMap<TreeNode, FunctionMbsVO>();
	private Map<FunctionMbsVO, TreeNode> functionToNodeMap = new HashMap<FunctionMbsVO, TreeNode>();

	// private DMSNode selectedNode;

	public TreeBeanMbs(List<FunctionMbsVO> functionsmbs) {
		this.functionsList = functionsmbs;
		root = new DefaultTreeNode("Root", null);

		Map<String, TreeNode> baseFuncToNodeMap = new HashMap<String, TreeNode>();

		for (FunctionMbsVO funct : functionsList) {
			String baseFunction = funct.getBaseFunc();
			if (!baseFuncToNodeMap.containsKey(baseFunction)) {
				TreeNode baseNode = new DefaultTreeNode(baseFunction, root);
				baseNode.setExpanded(false);
				//baseFuncToNodeMap.put(baseFunction, baseNode);
				baseFuncToNodeMap.put(baseFunction, baseNode);
			}
			TreeNode baseNode = baseFuncToNodeMap.get(baseFunction);
			createFunctionNode(baseNode, funct);
		}
	}

	public List<FunctionMbsVO> getSelectedFunctions() {
		List<FunctionMbsVO> functionsmbs = new ArrayList<FunctionMbsVO>();
		for (TreeNode treeNode : selectedNodes) {
			FunctionMbsVO functionVO = nodeToFunctionMap.get(treeNode);
			if (functionVO == null) {
				// ignore this as it corresponds to base function node in the
				// tree
				continue;
			}
			functionsmbs.add(functionVO);
		}

		return functionsmbs;
	}

	private void createFunctionNode(TreeNode baseNode, FunctionMbsVO funct) {
		TreeNode functionNode = new DefaultTreeNode(funct, baseNode);
		nodeToFunctionMap.put(functionNode, funct);
		functionToNodeMap.put(funct, functionNode);
	}

	public TreeNode getRoot() {
		return root;
	}

	public TreeNode[] getSelectedNodes() {
		return selectedNodes;
	}

	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;

	}

	public void displaySelectedSingle() {
		if (selectedNodes != null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Selected", selectedNodes[selectedNodes.length - 1]
							.getData().toString());

			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void deleteNode() {
		// selectedNodes.getChildren().clear();
		// selectedNodes.getParent().getChildren().remove(selectedNodes);
		// selectedNodes.setParent(null);
		//
		// selectedNodes = null;
	}

	public void setSelectedFunctions(List<FunctionMbsVO> functionsmbs) {
		if (functionsmbs != null) {
			selectedNodes = new TreeNode[functionsmbs.size()];
			int i = 0;
			for (FunctionMbsVO selectedFunction : functionsmbs) {
				TreeNode functionNode = functionToNodeMap.get(selectedFunction);
				selectedNodes[i] = functionNode;
				functionNode.setSelected(true);
				i++;
			}
		} else {
			selectedNodes = null;
		}
	}

	public void select(NodeSelectEvent event) {
		TreeNode treeNode = event.getTreeNode();
		List<TreeNode> l = new ArrayList<TreeNode>(Arrays.asList(selectedNodes));
		l.add(treeNode);

		selectedNodes = l.toArray(new TreeNode[1]);
	}

	public void unselect(NodeUnselectEvent event) {
		TreeNode treeNode = event.getTreeNode();
		List<TreeNode> l = new ArrayList<TreeNode>(Arrays.asList(selectedNodes));
		l.remove(treeNode);

		selectedNodes = l.toArray(new TreeNode[1]);
	}

	/*
	 * public void onNodeSelect(NodeSelectEvent event) { selectedNode =
	 * (DMSNode) event.getTreeNode(); FacesMessage message = new
	 * FacesMessage(FacesMessage.SEVERITY_INFO, "Selected",
	 * event.getTreeNode().toString());
	 * FacesContext.getCurrentInstance().addMessage(null, message); }
	 * 
	 * public void onNodeUnselect(NodeUnselectEvent event) { FacesMessage
	 * message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected",
	 * event.getTreeNode().toString());
	 * FacesContext.getCurrentInstance().addMessage(null, message); }
	 */

}
