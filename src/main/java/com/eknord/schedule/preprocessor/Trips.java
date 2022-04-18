package com.eknord.schedule.preprocessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.eknord.schedule.data.Leg;

public class Trips {
    private Map<String, Set<Leg>> directedGraph = new HashMap<>();

    public Map<String, Set<Leg>> getDirectedGraph() {
        return directedGraph;
    }

    public void setDirectedGraph(Map<String, Set<Leg>> directedGraph) {
        this.directedGraph = directedGraph;
    }

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
    }

    public void printAllPathsUtil(String src, String dest, boolean[] isVisited, List<String> localPathList, List<String> allPaths, int loopNumber) {
 
        if (loopNumber > 0 && src.equalsIgnoreCase(dest)) {
            // System.out.println(localPathList);
            allPaths.add(localPathList.toString());
            // if match found then no need to traverse more till depth
            return;
        // } else {
        //     allPaths.add(localPathList.toString());
        }
 
        // Mark the current node
        isVisited[src.charAt(0)] = true;
 
        for (Leg l : directedGraph.get(src)) {
            if (!isVisited[l.getEndCity().charAt(0)]) {
                // store current node
                // in path[]
                localPathList.add(l.getEndCity());
                printAllPathsUtil(l.getEndCity(), dest, isVisited, localPathList, allPaths, 1);
 
                // remove current node
                // in path[]
                localPathList.remove(l.getEndCity());
            }
        }
 
        // Mark the current node
        isVisited[src.charAt(0)] = false;
    }
 

    // private boolean isReachable(String src, String dest, int loopNumber, boolean[] discovered, Stack<String> path) {
    //     discovered[src.charAt(0)] = true;
    //     path.add(src);
    //     if (loopNumber > 0 && src.equalsIgnoreCase(dest)) {
    //         return true;
    //     }
    //     for (Leg l : directedGraph.get(src)) {
    //         if (!discovered[l.getEndCity().charAt(0)]) {
    //             if (isReachable(l.getEndCity(), dest, 1, discovered, path)) {
    //                 return true;
    //             }
    //         }
    //     }
    //     path.pop();
    //     return false;
    // }

    public int getDistance(String startCity, String endCity) throws NumberFormatException {
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
