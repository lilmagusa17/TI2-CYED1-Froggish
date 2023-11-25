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
    public void strongConnected1() {
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
    public void strongConnected2() {
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

    @Test
    public void bfs1() {
        setupScenary1();
        //test BFS
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

    @Test
    public void bfs2() {
        setupScenary2();
        graph2.addVertex(1);
        graph2.addVertex(2);
        graph2.addVertex(3);
        graph2.addVertex(4);

        graph2.addEdge(1, 2);
        graph2.addEdge(1, 3);
        graph2.addEdge(3, 2);
        graph2.addEdge(2, 4);

        assertTrue(graph2.BFS(1));


    }

    @Test
    public void dfs1() {
        setupScenary1();
        //test DFS
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

    //test DFS
    @Test
    public void dfs2() {
        setupScenary2();
        graph2.addVertex(1);
        graph2.addVertex(2);
        graph2.addVertex(3);
        graph2.addVertex(4);

        graph2.addEdge(1, 2);
        graph2.addEdge(1, 3);
        graph2.addEdge(3, 2);
        graph2.addEdge(2, 4);

        assertEquals(1, graph2.DFS());

    }

    @Test
    public void dijkstra1(){
        setupScenary1();
        //test dijkstra
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

        assertEquals(1, matrix[0][1]);
    }

    //test dijkstra
    @Test
    public void dijkstra2() {
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

        int[][] matrix = graph2.dijkstra(1);

        assertEquals(1, matrix[0][1]);

    }

    @Test
    public void floyd1() {
        setupScenary1();
        //test floidWarshall
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("C", "B");
        graph.addEdge("B", "D");
        graph.addEdge("D", "A");

        int[][] matrix = graph.floydWarshall();

        assertEquals(1, matrix[0][2]);

    }

    //test floidWarshall
    @Test
    public void floyd2() {
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

        int[][] matrix = graph2.floydWarshall();

        assertEquals(1, matrix[0][2]);


    }

}
