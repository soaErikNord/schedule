package com.eknord.schedule.preprocessor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.eknord.schedule.data.Leg;

public class Trips {
    private Map<String, Set<Leg>> directedGraph = new HashMap<>();

    public Trips() {

    }

    public void buildGraph(String waypoints) {
        System.out.println("waypoints: " + waypoints);
        if (waypoints == null) {
            waypoints = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        }
        String[] inbounds = waypoints.split(",");
        List<Leg> legs = new ArrayList<>();
        for (int i = 0; i < inbounds.length; i++) {
            System.out.println(inbounds[i]);
            String waypoint = inbounds[i].strip();
            legs.add(new Leg(waypoint.substring(0, 1), waypoint.substring(1, 2), Integer.parseInt(waypoint.substring(2))));
        }
        this.setGraph(legs);
    }

    public void setGraph(List<Leg> legs) {
        for (Leg leg : legs) {
            if(directedGraph.get(leg.getStartCity()) == null) {
                directedGraph.put(leg.getStartCity(), new HashSet<>());
            }
        }

        for (Leg leg : legs) {
            directedGraph.get(leg.getStartCity()).add(leg);
        }

        printGraph();
    }

    public void printGraph() {
        for (Map.Entry<String, Set<Leg>> entry : directedGraph.entrySet()) {
            Iterator<Leg> itr = entry.getValue().iterator();
            while (itr.hasNext()) {
                Leg newLeg = itr.next();
                System.out.println("(" + entry.getKey() + " ——> " + newLeg.getEndCity() + ")\t");
            }
        }

        // The distance of the route A-B-C.
        try {
            System.out.print("Output #1: ");
            System.out.println(getDistance("A", "B") + getDistance("B", "C"));
        } catch (NumberFormatException nfe) {
            System.out.println("NO SUCH ROUTE");
        }

        // The distance of the route A-D.
        try {
            System.out.print("Output #2: ");
            System.out.println(getDistance("A", "D"));
        } catch (NumberFormatException nfe) {
            System.out.println("NO SUCH ROUTE");
        }

        // The distance of the route A-D-C.
        try {
            System.out.print("Output #3: ");
            System.out.println(getDistance("A", "D") + getDistance("D", "C"));
        } catch (NumberFormatException nfe) {
            System.out.println("NO SUCH ROUTE");
        }

        // The distance of the route A-E-B-C-D.
        try {
            System.out.print("Output #4: ");
            System.out.println(getDistance("A", "E") + getDistance("E", "B") + getDistance("B", "C") + getDistance("C", "D"));
        } catch (NumberFormatException nfe) {
            System.out.println("NO SUCH ROUTE");
        }

        // The distance of the route A-E-D.
        try {
            System.out.print("Output #5: ");
            System.out.println(getDistance("A", "E") + getDistance("E", "D"));
        } catch (NumberFormatException nfe) {
            System.out.println("NO SUCH ROUTE");
        }

        // The number of trips starting at C and ending at C with a maximum of 3 stops.
        // In the sample data below, there are two such trips: C-D-C (2 stops). and
        // C-E-B-C (3 stops).
        // TODO
        try {
            System.out.print("Output #6: ");
            System.out.println(getDistance("A", "E") + getDistance("E", "D"));
        } catch (NumberFormatException nfe) {
            System.out.println("NO SUCH ROUTE");
        }

        // The number of trips starting at A and ending at C with exactly 4 stops. In the
        // sample data below, there are three such trips: A to C (via B,C,D); A to C (via
        // D,C,D); and A to C (via D,E,B).
        try {
            System.out.print("Output #7: ");
            String src = "A";
            String dest = "C";
            boolean[] isVisited = new boolean[100];
            ArrayList<String> pathList = new ArrayList<>();
            ArrayList<String> allPaths = new ArrayList<>();
    
            // add source to path[]
            pathList.add(src);
    
            // Call recursive utility
            printAllPathsUtil(src, dest, isVisited, pathList, allPaths);

            int routes = 0;
            for (String s : allPaths) {
                if (s.length() == 15) {
                    routes++;
                }
            }
            System.out.println(routes);
        } catch (NumberFormatException nfe) {
            System.out.println("NO SUCH ROUTE");
        }

        // The length of the shortest route (in terms of distance to travel) from A to C.
        // TODO
        try {
            System.out.print("Output #8: ");
            String src = "A";
            String dest = "C";
            boolean[] discovered = new boolean[100];
            Stack<String> path = new Stack<>();
            if (isReachable(src, dest, 0, discovered, path)) {
                System.out.println("The complete path is " + path);
            }
        } catch (NumberFormatException nfe) {
            System.out.println("NO SUCH ROUTE");
        }

        // The length of the shortest route (in terms of distance to travel) from B to B.
        // TODO
        try {
            System.out.print("Output #9: ");
            String src = "B";
            String dest = "B";
            boolean[] discovered = new boolean[100];
            Stack<String> path = new Stack<>();
            if (isReachable(src, dest, 0, discovered, path)) {
                System.out.println("The complete path is " + path);
            }
        } catch (NumberFormatException nfe) {
            System.out.println("NO SUCH ROUTE");
        }

        // The number of different routes from C to C with a distance of less than 30. In
        // the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC,
        // CEBCEBC, CEBCEBCEBC.
        // TODO
        try {
            System.out.print("Output #10: ");

            Set<Leg> cLegs = directedGraph.get("C");
            Iterator<Leg> legSet = cLegs.iterator();

            Map<String, Set<Leg>> allCRoutes = new HashMap<>();
            allCRoutes.put("C", cLegs);

            String src = "C";
            String dest = "B";
            boolean[] discovered = new boolean[100];
            Stack<String> path = new Stack<>();
            if (isReachable(src, dest, 0, discovered, path)) {
                System.out.println("Path exists from vertex " + src + " to vertex " + dest);
                System.out.println("The complete path is " + path);
            }

            // this.getAllRoutesForDestination(allCRoutes, )
            

            while (legSet.hasNext()) {
                Leg newLeg = legSet.next();
                System.out.println("Leg: " + newLeg.getStartCity() + newLeg.getEndCity());
                Set<Leg> nextLeg = directedGraph.get(newLeg.getEndCity());
                allCRoutes.put(newLeg.getEndCity(), nextLeg);
            //     allCRoutes.put(newLeg.getEndCity(), directedGraph.get(newLeg.getEndCity()));
            //     // Iterator<Leg> nextLegIterator = nextLeg.iterator();
            //     // while (nextLegIterator.hasNext()) {
            //     //     Leg newLeg1 = nextLegIterator.next();
            //     //     Set<Leg> nextLeg1 = directedGraph.get(newLeg1.getEndCity());
            //     //     allCRoutes.put(newLeg1.getStartCity(), nextLeg1);
            //     // }
            }

            for (Map.Entry<String, Set<Leg>> entry : allCRoutes.entrySet()) {
                Iterator<Leg> itr = entry.getValue().iterator();
                while (itr.hasNext()) {
                    Leg newLeg = itr.next();
                    System.out.print("(" + entry.getKey() + " ——> " + newLeg.getEndCity() + ")\t");
                }
            }

            System.out.println(getDistance("A", "E") + getDistance("E", "D"));
        } catch (NumberFormatException nfe) {
            System.out.println("NO SUCH ROUTE");
        }
    }

    private void printAllPathsUtil(String src, String dest, boolean[] isVisited, List<String> localPathList, List<String> allPaths) {
 
        if (src.equalsIgnoreCase(dest)) {
            // System.out.println(localPathList);
            allPaths.add(localPathList.toString());
            // if match found then no need to traverse more till depth
            return;
        }
 
        // Mark the current node
        isVisited[src.charAt(0)] = true;
 
        for (Leg l : directedGraph.get(src)) {
            if (!isVisited[l.getEndCity().charAt(0)]) {
                // store current node
                // in path[]
                localPathList.add(l.getEndCity());
                printAllPathsUtil(l.getEndCity(), dest, isVisited, localPathList, allPaths);
 
                // remove current node
                // in path[]
                localPathList.remove(l.getEndCity());
            }
        }
 
        // Mark the current node
        isVisited[src.charAt(0)] = false;
    }
 

    private boolean isReachable(String src, String dest, int loopNumber, boolean[] discovered, Stack<String> path) {
        discovered[src.charAt(0)] = true;
        path.add(src);
        if (loopNumber > 0 && src.equalsIgnoreCase(dest)) {
            return true;
        }
        for (Leg l : directedGraph.get(src)) {
            if (!discovered[l.getEndCity().charAt(0)]) {
                if (isReachable(l.getEndCity(), dest, 1, discovered, path)) {
                    return true;
                }
            }
        }
        path.pop();
        return false;
    }

    // private void getAllRoutesForDestination(Map<String, Set<Leg>> allCRoutes, boolean b) {
    //     while (legSet.hasNext()) {
    //         Leg newLeg = legSet.next();
    //         System.out.println("Leg: " + newLeg.getStartCity() + newLeg.getEndCity());
    //         Set<Leg> nextLeg = directedGraph.get(newLeg.getEndCity());
    //         allCRoutes.put(newLeg.getEndCity(), nextLeg);
    //     //     allCRoutes.put(newLeg.getEndCity(), directedGraph.get(newLeg.getEndCity()));
    //     //     // Iterator<Leg> nextLegIterator = nextLeg.iterator();
    //     //     // while (nextLegIterator.hasNext()) {
    //     //     //     Leg newLeg1 = nextLegIterator.next();
    //     //     //     Set<Leg> nextLeg1 = directedGraph.get(newLeg1.getEndCity());
    //     //     //     allCRoutes.put(newLeg1.getStartCity(), nextLeg1);
    //     //     // }
    //     }
    // }

    private int getDistance(String startCity, String endCity) throws NumberFormatException {
        Iterator<Leg> legSet = directedGraph.get(startCity).iterator();
        while (legSet.hasNext()) {
            Leg newLeg = legSet.next();
            if (newLeg.getEndCity().equalsIgnoreCase(endCity)) {
                return newLeg.getDistance();
            }
        }
        throw new NumberFormatException();
    }
}
