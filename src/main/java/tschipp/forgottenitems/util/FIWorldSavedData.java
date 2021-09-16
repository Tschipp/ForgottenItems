package tschipp.forgottenitems.util;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;
import tschipp.forgottenitems.FIM;

public class FIWorldSavedData extends WorldSavedData {
	
	private static final String DATA_NAME = FIM.MODID.toUpperCase() + "_DATA";
	private long seed;
	private ArrayList<String> forbiddenList;
	
	public FIWorldSavedData() {
		super(DATA_NAME);
	}
	
	public FIWorldSavedData(String name) {
		super(name);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		this.seed = tag.getLong("seed");
		this.forbiddenList = new ArrayList<String>(Arrays.asList(tag.getString("forbidden").split(", ")));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setLong("seed", this.seed);
		tag.setString("forbidden", this.forbiddenList.toString().replace("]", "").replace("[", ""));
		return tag;
	}
	
	
	
	public void setSeed(long seed)
	{
		this.seed = seed;
	}
	
	public long getSeed()
	{
		return this.seed;
	}
	
	public void setForbidden(ArrayList<String> forbiddenList)
	{
		this.forbiddenList = forbiddenList;
	}
	
	public ArrayList<String> getForbidden()
	{
		return this.forbiddenList;
	}
	
	
	
	public static FIWorldSavedData getInstance(World world) {

		MapStorage storage = world.getPerWorldStorage();
		FIWorldSavedData instance = (FIWorldSavedData) storage.getOrLoadData(FIWorldSavedData.class, DATA_NAME);

		  if (instance == null) {
		    instance = new FIWorldSavedData();
		    storage.setData(DATA_NAME, instance);
		  }
		  return instance;
		}

}
