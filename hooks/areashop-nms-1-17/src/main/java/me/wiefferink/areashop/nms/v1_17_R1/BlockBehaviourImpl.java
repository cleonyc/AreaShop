package me.wiefferink.areashop.nms.v1_17_R1;

import me.wiefferink.areashop.nms.BlockBehaviourHelper;
import net.minecraft.core.BlockPosition;
import net.minecraft.world.level.World;
import net.minecraft.world.level.block.state.IBlockData;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_17_R1.block.CraftBlockState;
import org.bukkit.craftbukkit.v1_17_R1.block.data.CraftBlockData;

import java.util.Objects;

public class BlockBehaviourImpl implements BlockBehaviourHelper {

    @Override
    public boolean canPlace(Location location, BlockData blockData) {
        Objects.requireNonNull(location.getWorld(), "Null World!");
        final BlockPosition blockPosition = new BlockPosition(location.getX(), location.getY(), location.getZ());
        final CraftWorld craftWorld = (CraftWorld) location.getWorld();
        final World world = craftWorld.getHandle();
        final CraftBlockData craftBlockData = (CraftBlockData) blockData;
        final IBlockData ibd = craftBlockData.getState();
        return ibd.canPlace(world, blockPosition);
    }

    @Override
    public boolean isBlockValid(Block block) {
        final CraftBlock craftBlock = (CraftBlock) block;
        final CraftWorld craftWorld = (CraftWorld) craftBlock.getWorld();
        final World world = craftWorld.getHandle();
        final IBlockData ibd = craftBlock.getNMS();
        return ibd.canPlace(world, craftBlock.getPosition());
    }

    @Override
    public boolean isBlockStateValid(BlockState blockState) {
        final CraftBlockState craftBlockState = (CraftBlockState) blockState;
        final IBlockData ibd = craftBlockState.getHandle();
        return ibd.canPlace(craftBlockState.getBlock().getCraftWorld().getHandle(), craftBlockState.getPosition());
    }

}