package com.example.system.security;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/monitor")
public class MonitorController {

    private final SystemInfo systemInfo = new SystemInfo();
    private final HardwareAbstractionLayer hardware = systemInfo.getHardware();
    private final OperatingSystem os = systemInfo.getOperatingSystem();
    private long[] prevTicks = new long[CentralProcessor.TickType.values().length];

    // 获取系统信息
    @GetMapping("/system")
    public Map<String, Object> getSystemInfo() {
        Map<String, Object> systemInfoMap = new HashMap<>();
        systemInfoMap.put("os", os);
        systemInfoMap.put("uptime", os.getSystemUptime());
        systemInfoMap.put("bootTime", new Date(os.getSystemBootTime() * 1000L));
        systemInfoMap.put("currentTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return systemInfoMap;
    }

    // 获取CPU信息
    @GetMapping("/cpu")
    public Map<String, Object> getCpuInfo() {
        CentralProcessor processor = hardware.getProcessor();
        Map<String, Object> cpuInfoMap = new HashMap<>();
        cpuInfoMap.put("cpuModel", processor.getProcessorIdentifier().getName());
        cpuInfoMap.put("physicalCores", processor.getPhysicalProcessorCount());
        cpuInfoMap.put("logicalCores", processor.getLogicalProcessorCount());

        long[] newTicks = processor.getSystemCpuLoadTicks();
        double systemCpuLoad = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
        prevTicks = newTicks;

        cpuInfoMap.put("systemCpuLoad", systemCpuLoad);
        return cpuInfoMap;
    }

    // 获取内存信息
    @GetMapping("/memory")
    public Map<String, Object> getMemoryInfo() {
        GlobalMemory memory = hardware.getMemory();
        Map<String, Object> memoryInfoMap = new HashMap<>();
        memoryInfoMap.put("totalMemory", memory.getTotal());
        memoryInfoMap.put("availableMemory", memory.getAvailable());
        memoryInfoMap.put("usedMemory", memory.getTotal() - memory.getAvailable());
        return memoryInfoMap;
    }

    // 获取交换区信息
    @GetMapping("/swap")
    public Map<String, Object> getSwapInfo() {
        GlobalMemory memory = hardware.getMemory();
        Map<String, Object> swapInfoMap = new HashMap<>();
        swapInfoMap.put("totalSwap", memory.getVirtualMemory().getSwapTotal());
        swapInfoMap.put("usedSwap", memory.getVirtualMemory().getSwapUsed());
        return swapInfoMap;
    }

    // 获取磁盘信息
    @GetMapping("/disk")
    public List<HWDiskStore> getDiskInfo() {
        return hardware.getDiskStores();
    }

    // 获取当前时间
    @GetMapping("/current-time")
    public Map<String, Object> getCurrentTime() {
        Map<String, Object> timeInfoMap = new HashMap<>();
        timeInfoMap.put("currentTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return timeInfoMap;
    }
}
