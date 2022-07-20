package com.bs.regsystemapi.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @Description : SnowFlake算法
 */
@Slf4j
public class SnowFlake {

	private static final long TWEPOCH = 12888349746579L;
	private static final long WORKER_ID_BITS = 5L;
	private static final long DATACENTER_ID_BITS = 5L;
	private static final long SEQUENCE_BITS = 12L;
	private static final long WORKER_ID_SHIFT = 12L;
	private static final long DATACENTER_ID_SHIFT = 17L;
	private static final long TIMESTAMP_LEFT_SHIFT = 22L;
	private static final long SEQUENCE_MASK = 4095L;
	private static long LAST_TIMESTAMP = -1L;
	private long sequence = 0L;
	private long workerId = 1L;
	private static long workerMask = 31L;
	private long processId = 1L;
	private static long processMask = 31L;
	private static SnowFlake snowFlake = null;

	public static synchronized String nextId() {
		String flake = snowFlake.getNextId() + "";
		return flake.substring(5, 15);
	}

	private SnowFlake() {
		this.workerId = this.getMachineNum();
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		this.processId = Long.valueOf(runtimeMXBean.getName().split("@")[0]);
		this.workerId &= workerMask;
		this.processId &= processMask;
	}

	public synchronized long getNextId() {
		long timestamp = this.timeGen();
		if (timestamp < LAST_TIMESTAMP) {
			try {
				throw new Exception("Clock moved backwards.  Refusing to generate id for " + (LAST_TIMESTAMP - timestamp) + " milliseconds");
			} catch (Exception var5) {
				log.error("异常信息:", var5);
			}
		}

		if (LAST_TIMESTAMP == timestamp) {
			this.sequence = this.sequence + 1L & 4095L;
			if (this.sequence == 0L) {
				timestamp = this.tilNextMillis(LAST_TIMESTAMP);
			}
		} else {
			this.sequence = 0L;
		}

		LAST_TIMESTAMP = timestamp;
		long nextId = timestamp - 12888349746579L << 22 | this.processId << 17 | this.workerId << 12 | this.sequence;
		return nextId;
	}

	private long tilNextMillis(long lastTimestamp) {
		long timestamp;
		for (timestamp = this.timeGen(); timestamp <= LAST_TIMESTAMP; timestamp = this.timeGen()) {
		}

		return timestamp;
	}

	private long timeGen() {
		return System.currentTimeMillis();
	}

	private long getMachineNum() {
		StringBuilder sb = new StringBuilder();
		Enumeration e = null;

		try {
			e = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException var6) {
			var6.printStackTrace();
		}

		while (e.hasMoreElements()) {
			NetworkInterface ni = (NetworkInterface) e.nextElement();
			sb.append(ni.toString());
		}

		long machinePiece = (long) sb.toString().hashCode();
		return machinePiece;
	}

	static {
		snowFlake = new SnowFlake();
	}
}
