
import com.froggish.froggish.graph.AdjacencyMatrix;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;


public class AdjacencyMatrixTest {

    private AdjacencyMatrix<String> graph;

    public void setUp1() {
        graph = new AdjacencyMatrix<>();
    }

    public void setUp2() {
        graph = new AdjacencyMatrix<>();
        graph.addVertex("V0");
        graph.addVertex("V1");
        graph.addVertex("V2");
        graph.addEdge("V0", "V1");
    }

    public void setUp3() {
        graph = new AdjacencyMatrix<>();
        graph.addVertex("V0");
        graph.addVertex("V1");
        graph.addVertex("V2");
        graph.addEdge("V0", "V1");
        graph.addEdge("V1", "V2");
    }


    @Test
    public void testAddEdge1() {
        setUp1();
        assertNull(graph.getEdge("V0", "V1"));
    }

    @Test
    public void testAddEdge2() {
        setUp2();
        assertNotNull(graph.getEdge("V0", "V1"));
    }

    @Test
    public void testAddEdge3() {
        setUp3();
        assertNotNull(graph.getEdge("V1", "V2"));
    }

    @Test
    public void testDfs1() {
        setUp1();
        assertThrows(NullPointerException.class, () -> graph.dfs("V0"));
    }
    @Test
    public void testDfs2() {
        setUp2();
        assertEquals(2, graph.dfs("V0").size());
    }

    @Test
    public void testDfs3() {
        setUp3();
        assertEquals(3, graph.dfs("V0").size());
    }

    @Test
    public void testDijkstraSingleNode() {
        setUp1();

        Map<String, Integer> result = graph.dijkstra("V0");

        assertEquals(0, result.get("V0"));
    }

    @Test
    public void testDijkstra2() {
        setUp2();
        assertEquals(3, graph.dijkstra("V0").size());
    }

    @Test
    public void testDijkstra3() {
        setUp3();
        assertEquals(3, graph.dijkstra("V0").size());
    }
}


