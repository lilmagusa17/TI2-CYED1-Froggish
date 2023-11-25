import com.froggish.froggish.graph.GraphAdjacencyList;
import com.froggish.froggish.graph.GraphInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GraphAdjacencyListTest {
    private GraphAdjacencyList<String> graph;

    private GraphAdjacencyList<Integer> graph2;

    private void setupScenary1() {
        graph = new GraphAdjacencyList<>();
    }

    private void setupScenary2() {
        graph2 = new GraphAdjacencyList<>();
    }





    @Test
    public void test1() {
        setupScenary1();
        //Adding the nodes in the graph
        graph.addVertex("U");
        graph.addVertex("V");
        graph.addVertex("X");
        graph.addVertex("Y");
        graph.addVertex("Z");
        //Adding the edges in the graph
        graph.addEdge("U", "X");
        graph.addEdge("X", "Y");
        graph.addEdge("Y", "Z");
        graph.addEdge("Z", "Y");
        graph.addEdge("Y", "V");
        graph.addEdge("V", "U");

        assertTrue(graph.isStronglyConnected());
    }

    @Test
    public void test2() {
        setupScenary1();

        //Adding the nodes in the graph
        graph.addVertex("U");
        graph.addVertex("V");
        graph.addVertex("X");
        graph.addVertex("Y");
        graph.addVertex("Z");
        //Adding the edges in the graph
        graph.addEdge("U", "X");
        graph.addEdge("X", "Y");
        graph.addEdge("Y", "Z");
        graph.addEdge("Z", "Y");
        //graph.addEdge("Y", "V");
        graph.addEdge("V", "U");

        assertFalse(graph.isStronglyConnected());
    }

    @Test
    public void test3() {
        setupScenary1();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("C", "B");

        assertFalse(graph.isStronglyConnected());

    }

    @Test
    public void test4() {
        setupScenary1();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("C", "B");
        graph.addEdge("B", "D");
        graph.addEdge("D", "A");

        assertTrue(graph.isStronglyConnected());

    }

    @Test
    public void test5() {
        setupScenary2();
        graph2.addVertex(1);
        graph2.addVertex(2);
        graph2.addVertex(3);
        graph2.addVertex(4);

        graph2.addEdge(1, 2);
        graph2.addEdge(1, 3);
        graph2.addEdge(3, 2);
        graph2.addEdge(2, 4);
        graph2.addEdge(4, 1);
        graph2.addEdge(4, 3);

        assertTrue(graph2.isStronglyConnected());

    }

    //test DFS
    @Test
    public void test6() {
        setupScenary1();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("C", "B");
        graph.addEdge("B", "D");
        graph.addEdge("D", "A");

        assertEquals(1, graph.DFS());

    }

    //test BFS
    @Test
    public void test7() {
        setupScenary1();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("C", "B");
        graph.addEdge("B", "D");
        graph.addEdge("D", "A");

        assertTrue(graph.BFS("A"));

    }

    //test dijkstra
    @Test
    public void test8() {
        setupScenary1();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("C", "B");
        graph.addEdge("B", "D");
        graph.addEdge("D", "A");

        int[][] matrix = graph.dijkstra("A");

        assertEquals(0, matrix[0][0]);
        assertEquals(1, matrix[0][1]);
        assertEquals(1, matrix[0][2]);
        assertEquals(0, matrix[0][3]);

        assertEquals(0, matrix[1][0]);
        assertEquals(0, matrix[1][1]);
        assertEquals(0, matrix[1][2]);
        assertEquals(1, matrix[1][3]);

        assertEquals(0, matrix[2][0]);
        assertEquals(1, matrix[2][1]);
        assertEquals(0, matrix[2][2]);
        assertEquals(0, matrix[2][3]);

        assertEquals(1, matrix[3][0]);
        assertEquals(0, matrix[3][1]);
        assertEquals(0, matrix[3][2]);
        assertEquals(0, matrix[3][3]);

    }

    //test floydWarshall

}
