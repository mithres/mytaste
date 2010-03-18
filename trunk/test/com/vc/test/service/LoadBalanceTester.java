package com.vc.test.service;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.vc.service.cluster.ILoadBalancer;
import com.vc.test.core.BaseTest;
import com.vc.vo.LBNode;

public class LoadBalanceTester extends BaseTest {

	@Autowired
	private ILoadBalancer loadBalancer = null;

	private class LoadBalancerCheckerThreadTester extends TestRunnable {

		private ILoadBalancer loadBalancer = null;

		public LoadBalancerCheckerThreadTester(ILoadBalancer loadBalancer) {
			this.loadBalancer = loadBalancer;
		}

		@Override
		public void runTest() throws Throwable {
			for (int i = 0; i < 10; i++) {
				System.out.println("Check status");
				LBNode[] nodes = loadBalancer.getAllLBNodes();

				Socket socket = null;
				List<LBNode> nodeNeedToRemove = new ArrayList<LBNode>();

				for (LBNode node : nodes) {
					try {
						System.out.println("Check node:" + node.toString());
						socket = new Socket();
						socket.setSoTimeout(1000);
						socket.connect(new InetSocketAddress(node.getNodeIP(), node.getPort()),1000);
					} catch (Exception e) {
						System.out.println(node.toString() + " error remove it.");
						nodeNeedToRemove.add(node);
					}
				}
				socket = null;
				if (nodeNeedToRemove.size() > 0) {
					loadBalancer.unregisterLBNode(nodeNeedToRemove);
				}
				Thread.sleep(5000);
			}
		}
	}

	private class MutiThreadTester extends TestRunnable {

		private ILoadBalancer loadBalancer = null;

		private LBNode node = null;

		public MutiThreadTester(ILoadBalancer loadBalancer, String ip, int port, String protocol) {
			this.loadBalancer = loadBalancer;
			node = new LBNode(ip, port, protocol);
		}

		@Override
		public void runTest() throws Throwable {
			long l;
			l = Math.round(2 + Math.random() * 3);
			// Sleep between 2-5 seconds
			Thread.sleep(l * 1000);
			System.out.println("Regist load balance node:" + node.getNodeIP());
			loadBalancer.registerLBNode(node);
			for (int i = 0; i < 50; i++) {
				LBNode tempNode = loadBalancer.getLBNode();
				System.out.println("Get lb from set:" + tempNode.getNodeIP() + ":" + tempNode.getConnections());
				Random random = new Random();
				int x = random.nextInt(10);
				for (int j = 0; j < x; j++) {
					loadBalancer.increaseConnection(tempNode);
					Thread.sleep(500);
				}
			}
		}
	}

	@Test
	public void testLoadBalance() {
		TestRunnable tr1, tr2, tr3, tr4, tr5, tr6;
		tr1 = new MutiThreadTester(loadBalancer, "172.0.3.191", 1935, "rtmp");
		tr2 = new MutiThreadTester(loadBalancer, "172.0.3.192", 1935, "rtmp");
		tr3 = new MutiThreadTester(loadBalancer, "172.0.2.193", 1935, "rtmp");
		tr4 = new MutiThreadTester(loadBalancer, "172.0.3.194", 1935, "rtmp");
		tr5 = new MutiThreadTester(loadBalancer, "172.0.2.195", 1935, "rtmp");
		tr6 = new LoadBalancerCheckerThreadTester(loadBalancer);

		TestRunnable[] trs = { tr1, tr2, tr3, tr4, tr5, tr6 };
		MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trs);
		try {
			mttr.runTestRunnables();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
