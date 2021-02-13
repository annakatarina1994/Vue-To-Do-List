package com.techelevator.model;


public class Recipe {

	private int recipeId;
	private int apiRecipeId;
	private int userId;
	private String title;
	private String instructions;
	
	
	public Recipe(int recipeId, int apiRecipeId, int userId, String title, String instructions) {
		super();
		this.recipeId = recipeId;
		this.apiRecipeId = apiRecipeId;
		this.userId = userId;
		this.title = title;
		this.instructions = instructions;
	}
	
	public Recipe () {}
	
	public int getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getApiRecipeId() {
		return apiRecipeId;
	}

	public void setApiRecipeId(int apiRecipeId) {
		this.apiRecipeId = apiRecipeId;
	}

	@Override
	public String toString() {
		return "Recipe [recipeId=" + recipeId + ", name=" + title + ", instructions="
				+ instructions + "]";
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
}
