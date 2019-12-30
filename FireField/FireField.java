/**
 * A Grass Field is divided into N x M Blocks of size 1. Some of the blocks in the field have caught fire. Now, in each second, the fire is spreading to the adjacent Blocks i.e. if (i , j) Block is on fire at time t, then Blocks (i + 1, j) , (i - 1, j) , (i , j - 1) , (i , j + 1) will be on fire at time t+1.
 *
 * You will be provided with the current configuration of the field at time t = 0. Your task is to determine the minimum time in which the whole field catches fire.
 * M[i][j] = 1 , if the block is on fire.
 * M[i][j] = 0 , if the block is not on fire.
 *
 *
 * Input Format
 * The First line will contain two Integers N and M denoting dimensions of the field.
 * Each of the next N lines will contain M integers.
 *
 *
 * Constraints
 * 1 <= N,M <= 500
 * M[i][j] = {0,1}
 *
 *
 * Output Format
 * You have to print the Minimum Time in which the whole field catches fire.
 * If the complete field never catches fire, output -1.
 *
 *
 * Sample TestCase 1
 * Input
 * 3 3
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * Output
 * 2
 */

import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class FireField {
	private static int[][] a;
	private static int M;
	private static int N;


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		M = scanner.nextInt();
		N = scanner.nextInt();
		a = new int[M][N];

		Set<Block> fireBlocks = new HashSet<>();

		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++) {
				a[i][j] = scanner.nextInt();
				if (a[i][j] == 1) {
					Block block = new Block(i, j);
					fireBlocks.add(block);
					a[i][j] = 1;
				}
			}

		System.out.println(getTotalTimeToCoverField(fireBlocks));
	}

	private static int getTotalTimeToCoverField(Set<Block> fireBlocks) {
		int result = 0;
		int blocksLeft = a.length * a[0].length - fireBlocks.size();

		while (blocksLeft > 0) {
			fireBlocks = coverForASecond(fireBlocks);
			blocksLeft -= fireBlocks.size();
			result++;
		}

		return result;
	}

	private static Set<Block> coverForASecond(Set<Block> fireBlocks) {
		Set<Block> nextFireBlocks = new HashSet<>();
		for (Block block : fireBlocks) {
			int i = block.getI();
			int j = block.getJ();

			stepper(nextFireBlocks, i + 1, j);
			stepper(nextFireBlocks, i - 1, j);
			stepper(nextFireBlocks, i , j + 1);
			stepper(nextFireBlocks, i , j - 1);

		}

		return nextFireBlocks;
	}

	private static void stepper(Set<Block> nextFireBlocks, int i, int j) {
		if (isValidMove(i, j) &&
				a[i][j] == 0) {
			nextFireBlocks.add(new Block(i, j));
			a[i][j] = 1;
		}
	}

	private static boolean isValidMove(int i, int j) {
		return !(i >= M || j >= N || i < 0 || j < 0);
	}
}


class Block {
	private int i;
	private int j;

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	public Block(int i, int j) {
		this.i = i;
		this.j = j;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Block block = (Block) o;
		return i == block.i &&
				j == block.j;
	}

	@Override
	public int hashCode() {
		return Objects.hash(i, j);
	}
}
