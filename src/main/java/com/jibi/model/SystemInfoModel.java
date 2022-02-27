package com.jibi.model;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class SystemInfoModel {

  private CpuInfoModel cpu;
  private OsInfoModel os;
  private MemoryInfoModel memory;
  private Map<String, DiskInfoModel> disks;

  public void setCpu(CpuInfoModel cpu) {
    this.cpu = cpu.clone();
  }

  public void setOs(OsInfoModel os) {
    this.os = os.clone();
  }

  public void setMemory(MemoryInfoModel memory) {
    this.memory = memory.clone();
  }

  public void setDisks(Map<String, DiskInfoModel> disks) {
    Map<String, DiskInfoModel> disksCopy = new HashMap<>();
    disksCopy.putAll(disks);
    this.disks = disksCopy;
  }

  public CpuInfoModel getCpu() {
    return cpu.clone();
  }

  public OsInfoModel getOs() {
    return os.clone();
  }

  public MemoryInfoModel getMemory() {
    return memory.clone();
  }

  public Map<String, DiskInfoModel> getDisks() {
    Map<String, DiskInfoModel> disksCopy = new HashMap<>();
    disksCopy.putAll(disks);
    return disksCopy;
  }

  @Setter
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class CpuInfoModel implements Cloneable {
    private String processCpuLoad;
    private String processCpuTime;
    private String systemCpuLoad;
    private String systemLoadAverage;

    @Override
    protected CpuInfoModel clone() {
      return new CpuInfoModel(processCpuLoad, processCpuTime, systemCpuLoad, systemLoadAverage);
    }
  }

  @Setter
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class OsInfoModel implements Cloneable {
    private String name;
    private String version;
    private String arch;
    private String processors;

    @Override
    protected OsInfoModel clone() {
      return new OsInfoModel(name, version, arch, processors);
    }
  }

  @Setter
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class MemoryInfoModel implements Cloneable {
    private String freeBytes;
    private String freeFormatted;
    private String allocatedBytes;
    private String allocatedFormatted;
    private String maxBytes;
    private String maxFormatted;
    private String usedBytes;
    private String usedFormatted;

    @Override
    protected MemoryInfoModel clone() {
      return new MemoryInfoModel(
          freeBytes,
          freeFormatted,
          allocatedBytes,
          allocatedFormatted,
          maxBytes,
          maxFormatted,
          usedBytes,
          usedFormatted);
    }
  }

  @Setter
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class DiskInfoModel implements Cloneable {
    private String freeSpace;
    private String freeSpaceFormatted;
    private String usableSpace;
    private String usableSpaceFormatted;
    private String totalSpace;
    private String totalSpaceFormatted;

    @Override
    protected DiskInfoModel clone() {
      return new DiskInfoModel(
          freeSpace,
          freeSpaceFormatted,
          usableSpace,
          usableSpaceFormatted,
          totalSpace,
          totalSpaceFormatted);
    }
  }
}
