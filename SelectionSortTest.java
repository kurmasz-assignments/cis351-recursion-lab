import org.junit.*;
import org.junit.rules.Timeout;
import java.util.concurrent.TimeUnit;

import static edu.gvsu.mipsunit.munit.MUnit.Register.*;
import static edu.gvsu.mipsunit.munit.MUnit.*;

public class SelectionSortTest {

    @Rule
    public Timeout globalTimeout= new Timeout(10, TimeUnit.SECONDS);

  /******************************************************************
   *
   * Test loc_of_min
   *
   *****************************************************************/

  @Test
  public void locOfMin_posNeg() {
    Label array = wordData(-7, 12, 11, 15, -9, -4, -14, -2, 1);
    run("loc_of_min", array, 9 );
    Assert.assertEquals(array.address() + 24, get(v0));
  }

  /******************************************************************
   *
   * Test selection_sort
   *
   *****************************************************************/

  Label array10 = wordData(5, 4, 7, 6, 9, 8, 2, 1, -1);
  Label array11 = wordData(3, 4, 7, 6, 9, 8, 2, 1, -1);


  @Test
  public void selectionSort_longer() {
    run("selection_sort", array10, 8);
    int[] observed = getWords(array10, 0, 9);
    Assert.assertArrayEquals(new int[]{1, 2, 4, 5, 6, 7, 8, 9, -1}, observed);
    Assert.assertTrue("Found unexpected memory modification", noOtherStaticDataModifications()); 
  }


  /******************************************************************
   *
   * Test sort_two
   *
   * This test verifies that selection_sort sets up and restores
   * the stack properly.
   *****************************************************************/

  @Test
  public void sortTwo() {
    Label array1 = wordData(8, 6, 7, 5, 3, 0, 9);
    Label array2 = wordData(6, 1, 6, 8, 9, 5, 5, 0, 0, 0);

    int initialStackPointer = get(sp);
    run("sort_two", array1, 7, array2, 10);
    int finalStackPointer = get(sp);
    int[] observed1 = getWords(array1, 0, 7);
    int[] observed2 = getWords(array2, 0, 10);
    Assert.assertArrayEquals(new int[]{0, 3, 5, 6, 7, 8, 9}, observed1);
    Assert.assertArrayEquals(new int[]{0, 0, 0, 1, 5, 5, 6, 6, 8, 9}, observed2);
    Assert.assertTrue("Found unexpected memory modification", noOtherStaticDataModifications()); 

    Assert.assertEquals("Initial and final stack pointers differ.", initialStackPointer, finalStackPointer);
    int[] theStack = getWords(initialStackPointer - 100, 100);
    Assert.assertTrue("You used too much stack", noOtherMemoryModifications()); 
  }
}