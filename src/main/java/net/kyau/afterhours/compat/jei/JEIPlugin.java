package net.kyau.afterhours.compat.jei;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;

@mezz.jei.api.JEIPlugin
public class JEIPlugin extends BlankModPlugin {

  @Override
  public void register(IModRegistry registry) {
    IJeiHelpers jeiHelpers = registry.getJeiHelpers();
    IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
    // quantum reciprocator
    registry.addRecipeCategories(new QuantumReciprocatorRecipeCategory(guiHelper));
    registry.addRecipeHandlers(new QuantumReciprocatorRecipeHandler());
    registry.addRecipes(QuantumReciprocatorRecipeMaker.getRecipes(jeiHelpers));
    // quantum stabilizer
    registry.addRecipeCategories(new QuantumStabilizerRecipeCategory(guiHelper));
    registry.addRecipeHandlers(new QuantumStabilizerRecipeHandler());
    registry.addRecipes(QuantumStabilizerRecipeMaker.getRecipes(jeiHelpers));
  }
}
