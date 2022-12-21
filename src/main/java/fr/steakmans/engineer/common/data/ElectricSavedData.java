package fr.steakmans.engineer.common.data;

import fr.steakmans.engineer.utils.ElectricCircuitUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElectricSavedData extends SavedData {

    public Map<Integer, ElectricCircuitUtil> map = new HashMap<>();

    @Nonnull
    public static ElectricSavedData get(Level level) {
        if(level.isClientSide) {
            throw new RuntimeException("This data is only accessible on server.");
        }
        DimensionDataStorage storage = level.getServer().overworld().getDataStorage();
        return storage.computeIfAbsent(ElectricSavedData::new, ElectricSavedData::new, "electricmanager");
    }

    public void createCircuit(int index) {
        map.put(index, new ElectricCircuitUtil(new ArrayList<BlockPos>(), new ArrayList<BlockPos>()));
        setDirty();
    }

    public void updateCircuit(int index, ElectricCircuitUtil circuit) {
        map.replace(index, circuit);
        setDirty();
    }

    public void removeCircuit(int index) {
        map.remove(index);
    }

    public ElectricSavedData() {}

    //load
    public ElectricSavedData(CompoundTag tag) {
        ListTag circuits = (ListTag) tag.get("circuits");
        map.clear();
        circuits.forEach(circuit -> {
            List<BlockPos> cables = new ArrayList<>();
            List<BlockPos> machines = new ArrayList<>();
            int index = ((CompoundTag)circuit).getInt("index");
            ListTag cable = (ListTag) ((CompoundTag)circuit).get("cables");
            cable.forEach(c -> {
                CompoundTag ca = (CompoundTag) c;
                BlockPos pos = new BlockPos(ca.getInt("X"), ca.getInt("Y"), ca.getInt("Z"));
                cables.add(pos);
            });
            ListTag machine = (ListTag) ((CompoundTag)circuit).get("machines");
            machine.forEach(c -> {
                CompoundTag ca = (CompoundTag) c;
                BlockPos pos = new BlockPos(ca.getInt("X"), ca.getInt("Y"), ca.getInt("Z"));
                machines.add(pos);
            });
            map.put(index, new ElectricCircuitUtil(machines, cables));
        });

    }

    @Override
    public CompoundTag save(CompoundTag tag1) {
        CompoundTag tag = new CompoundTag();

        ListTag circuits = new ListTag();

        map.forEach((index, circuit) -> {
            CompoundTag cir = new CompoundTag();
            cir.putInt("index", index);
            ListTag cables = new ListTag();
            circuit.getCables().forEach(pos -> {
                CompoundTag cable = new CompoundTag();
                cable.putInt("x", pos.getX());
                cable.putInt("y", pos.getY());
                cable.putInt("z", pos.getZ());
                cables.add(cable);
            });
            cir.put("cables", cables);
            ListTag machines = new ListTag();
            circuit.getMachines().forEach(pos -> {
                CompoundTag machine = new CompoundTag();
                machine.putInt("x", pos.getX());
                machine.putInt("y", pos.getY());
                machine.putInt("z", pos.getZ());
                machines.add(machine);
            });
            cir.put("machines", machines);
            circuits.add(cir);
        });

        tag.put("circuits", circuits);
        return tag;
    }

}
