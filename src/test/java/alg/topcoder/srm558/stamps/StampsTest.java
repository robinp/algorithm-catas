package alg.topcoder.srm558.stamps;

import org.junit.Test;

import static org.junit.Assert.*;

public class StampsTest {

  @Test
  public void test0() {
    final StampTask task = new StampTask(1, 1);
    final int stampCost = task.run("RRGGBB");
    assertEquals(5, stampCost);
  }
  
  @Test
  public void test1() {
    final StampTask task = new StampTask(1, 1);
    final int stampCost = task.run("R**GB*");
    assertEquals(5, stampCost);
  }

  @Test
  public void test2() {
    final StampTask task = new StampTask(2, 7);
    final int stampCost = task.run("BRRB");
    assertEquals(30, stampCost);
  }

  @Test
  public void test3() {
    final StampTask task = new StampTask(10, 58);
    final int stampCost = task.run("R*RR*GG");
    assertEquals(204, stampCost);
  }

  @Test
  public void test4() {
    final StampTask task = new StampTask(5, 2);
    final int stampCost = task.run("*B**B**B*BB*G*BBB**B**B*");
    assertEquals(33, stampCost);
  }

  @Test
  public void test5() {
    final StampTask task = new StampTask(7, 1);
    final int stampCost = task.run("*R*RG*G*GR*RGG*G*GGR***RR*GG");
    assertEquals(30, stampCost);
  }


}
