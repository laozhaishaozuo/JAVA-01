package top.shaozuo.geektime.java01.week11.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.hutool.core.util.StrUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

/**
 * 
 * 实现分数排名或者排行榜
 * 
 * @author shaozuo
 *
 */
public class ScoreRankSamples {

	private static final int SCORE_COUNT = 20;
	private static final String RANK_KEY = "2021:english";

	private static String printFormat = " Student ID:{}, English Score: {}";

	public static void main(String[] args) {
		String host = "localhost";
		String key = RANK_KEY;
		int port = 6379;
		Jedis jedis = new Jedis(host, port);
		try {
			jedis.del(RANK_KEY);
			List<String> studentIds = new ArrayList<String>();
			for (int i = 0; i < SCORE_COUNT; ++i) {
				studentIds.add(StrUtil.format("202103-{}", i));
			}
			System.out.println("Inputs all students ");
			for (int i = 0; i < studentIds.size(); i++) {
				int score = (int) (Math.random() * 100);
				String member = studentIds.get(i);
				print(member, score);
				jedis.zadd(key, score, member);
			}
			Set<Tuple> scoreList;
			printAllStudents(key, jedis);

			printTop(key, jedis, 5);

			printScores(key, jedis, 90, 100);

			printScores(key, jedis, 50, 60);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.quit();
			jedis.close();
		}
	}

	private static void printScores(String key, Jedis jedis, int start, int end) {
		Set<Tuple> scoreList;
		System.out.println();
		System.out.println(StrUtil.format(" key = {}", key));
		System.out.println(StrUtil.format(" Student with scores from {} to {}", start, end));

		scoreList = jedis.zrangeByScoreWithScores(key, start, end);
		for (Tuple item : scoreList) {
			print(item.getElement(), item.getScore());
		}
	}

	private static void printTop(String key, Jedis jedis, long top) {
		Set<Tuple> scoreList;
		System.out.println();
		System.out.println(StrUtil.format(" key = {}", key));
		System.out.println(StrUtil.format(" Top {} Student ", top));
		scoreList = jedis.zrevrangeWithScores(key, 0, top - 1L);
		for (Tuple item : scoreList) {
			print(item.getElement(), item.getScore());
		}
	}

	private static void printAllStudents(String key, Jedis jedis) {
		System.out.println();
		System.out.println(StrUtil.format(" key = {}", key));
		System.out.println(" Ranking list of all students");
		Set<Tuple> scoreList = jedis.zrevrangeWithScores(key, 0, -1);
		for (Tuple item : scoreList) {
			print(item.getElement(), item.getScore());
		}
	}

	private static void print(String id, double score) {
		System.out.println(StrUtil.format(printFormat, id, score));
	}
}
