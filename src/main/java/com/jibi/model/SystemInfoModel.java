package com.jibi.model;

import com.jibi.common.StringUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class SystemInfoModel {

    private CpuInfoModel cpu;
    private OsInfoModel os;
    private MemoryInfoModel memory;
    private Map<String, DiskInfoModel> disks;

    @Setter
    @Getter
    public static class CpuInfoModel {
        private String processCpuLoad;
        private String processCpuTime;
        private String systemCpuLoad;
        private String systemLoadAverage;
    }

    @Setter
    @Getter
    public static class OsInfoModel {
        private String name;
        private String version;
        private String arch;
        private String processors;
    }

    @Setter
    @Getter
    public static class MemoryInfoModel {
        private String freeBytes;
        private String freeFormatted;
        private String allocatedBytes;
        private String allocatedFormatted;
        private String maxBytes;
        private String maxFormatted;
        private String usedBytes;
        private String usedFormatted;
    }

    @Setter
    @Getter
    public static class DiskInfoModel {
        private String freeSpace;
        private String freeSpaceFormatted;
        private String usableSpace;
        private String usableSpaceFormatted;
        private String totalSpace;
        private String totalSpaceFormatted;
    }
}
