package com.eknord.schedule.preprocessor;

import java.util.ArrayList;

public class ScheduleRules {
    // Not web safe - needs to fixed
    // TODO
    private Trips trips = null;

    private void setUpTrips(String cities) throws ArrayIndexOutOfBoundsException {
        if (trips == null || trips.getDirectedGraph().size() <= 0) {
            if (cities == null || cities.equalsIgnoreCase("")) {
                throw new ArrayIndexOutOfBoundsException();
            }
            trips = new Trips();
            trips.buildGraph(cities);
        }
    }

    // The distance of the route A-B-C.
    public String getRule1(String cities) throws ArrayIndexOutOfBoundsException {
        setUpTrips(cities);

        try {
            return String.valueOf(trips.getDistance("A", "B") + trips.getDistance("B", "C"));
        } catch (NumberFormatException nfe) {
            return "NO SUCH ROUTE";
        }
    }

    // The distance of the route A-D.
    public String getRule2(String cities) {
        setUpTrips(cities);

        try {
            return String.valueOf(trips.getDistance("A", "D"));
        } catch (NumberFormatException nfe) {
            return "NO SUCH ROUTE";
        }
    }

    // The distance of the route A-D-C.
    public String getRule3(String cities) {
        setUpTrips(cities);

        try {
            return String.valueOf(trips.getDistance("A", "D") + trips.getDistance("D", "C"));
        } catch (NumberFormatException nfe) {
            return"NO SUCH ROUTE";
        }
    }

    // The distance of the route A-E-B-C-D.
    public String getRule4(String cities) {
        setUpTrips(cities);

        try {
            return String.valueOf(trips.getDistance("A", "E") 
                + trips.getDistance("E", "B") 
                + trips.getDistance("B", "C") 
                + trips.getDistance("C", "D"));
        } catch (NumberFormatException nfe) {
            return"NO SUCH ROUTE";
        }
    }

    // The distance of the route A-E-D.
    public String getRule5(String cities) {
        setUpTrips(cities);

        try {
            return String.valueOf(trips.getDistance("A", "E") + trips.getDistance("E", "D"));
        } catch (NumberFormatException nfe) {
            return "NO SUCH ROUTE";
        }
    }

    // The number of trips starting at C and ending at C with a maximum of 3 stops.
    // In the sample data below, there are two such trips: C-D-C (2 stops). and
    // C-E-B-C (3 stops).
    // TODO
    public String getRule6(String cities) {
        setUpTrips(cities);

        try {
            String src = "C";
            String dest = "C";
            boolean[] isVisited = new boolean[100];
            ArrayList<String> pathList = new ArrayList<>();
            ArrayList<String> allPaths = new ArrayList<>();
    
            // add source to path[]
            pathList.add(src);
    
            // Call recursive utility
            trips.printAllPathsUtil(src, dest, isVisited, pathList, allPaths, 0);

            int routes = 0;
            for (String s : allPaths) {
                System.out.println("s: " + s);
                if (s.length() == 12) {
                    routes++;
                }
            }
            return String.valueOf(routes);
        } catch (NumberFormatException nfe) {
            return "NO SUCH ROUTE";
        }
    }

    // The number of trips starting at A and ending at C with exactly 4 stops. In the
    // sample data below, there are three such trips: A to C (via B,C,D); A to C (via
    // D,C,D); and A to C (via D,E,B).
    public String getRule7(String cities) {
        setUpTrips(cities);

        try {
            String src = "A";
            String dest = "C";
            boolean[] isVisited = new boolean[100];
            ArrayList<String> pathList = new ArrayList<>();
            ArrayList<String> allPaths = new ArrayList<>();
    
            // add source to path[]
            pathList.add(src);
    
            // Call recursive utility
            trips.printAllPathsUtil(src, dest, isVisited, pathList, allPaths, 0);

            int routes = 0;
            for (String s : allPaths) {
                if (s.length() == 15) {
                    routes++;
                }
            }
            return String.valueOf(routes);
        } catch (NumberFormatException nfe) {
            return "NO SUCH ROUTE";
        }
    }

    // The length of the shortest route (in terms of distance to travel) from A to C.
    public String getRule8(String cities) {
        setUpTrips(cities);

        try {
            String src = "A";
            String dest = "C";
            boolean[] isVisited = new boolean[100];
            ArrayList<String> pathList = new ArrayList<>();
            ArrayList<String> allPaths = new ArrayList<>();
    
            // add source to path[]
            pathList.add(src);
    
            // Call recursive utility
            trips.printAllPathsUtil(src, dest, isVisited, pathList, allPaths, 0);

            String shortestRoute = "";
            int distance = 0;
            for (String s : allPaths) {
                if (s.length() < shortestRoute.length() || shortestRoute.equalsIgnoreCase("")) {
                    shortestRoute = s;
                    shortestRoute = shortestRoute.replace(",", "")
                        .replace("[", "")
                        .replace("]", "")
                        .replace(" ", "");
                    int tempDistance = 0;
                    for (int i = 0; i < shortestRoute.length() - 1; i++) {
                        tempDistance += trips.getDistance(String.valueOf(shortestRoute.charAt(i)), String.valueOf(shortestRoute.charAt(i+1)));
                    }
                    if (distance < tempDistance) {
                        distance = tempDistance;
                    }
                    shortestRoute = s;
                }
            }
            
            return String.valueOf(distance);
        } catch (NumberFormatException nfe) {
            return "NO SUCH ROUTE";
        }
    }

    // The length of the shortest route (in terms of distance to travel) from B to B.
    // TODO
    public String getRule9(String cities) {
        setUpTrips(cities);

        try {
            String src = "B";
            String dest = "B";
            boolean[] isVisited = new boolean[100];
            ArrayList<String> pathList = new ArrayList<>();
            ArrayList<String> allPaths = new ArrayList<>();
    
            // add source to path[]
            pathList.add(src);
    
            // Call recursive utility
            trips.printAllPathsUtil(src, dest, isVisited, pathList, allPaths, 0);

            String shortestRoute = "";
            int distance = 0;
            for (String s : allPaths) {
                if (s.length() < shortestRoute.length() || shortestRoute.equalsIgnoreCase("")) {
                    shortestRoute = s;
                    shortestRoute = shortestRoute.replace(",", "")
                        .replace("[", "")
                        .replace("]", "")
                        .replace(" ", "");
                    int tempDistance = 0;
                    for (int i = 0; i < shortestRoute.length() - 1; i++) {
                        tempDistance += trips.getDistance(String.valueOf(shortestRoute.charAt(i)), String.valueOf(shortestRoute.charAt(i+1)));
                    }
                    if (distance < tempDistance) {
                        distance = tempDistance;
                    }
                    shortestRoute = s;
                }
            }
            
            return String.valueOf(distance);
        } catch (NumberFormatException nfe) {
            return "NO SUCH ROUTE";
        }
    }

    // The number of different routes from C to C with a distance of less than 30. In
    // the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC,
    // CEBCEBC, CEBCEBCEBC.
    // TODO
    public String getRule10(String cities) {
        setUpTrips(cities);

        try {
            String src = "C";
            String dest = "C";
            boolean[] isVisited = new boolean[100];
            ArrayList<String> pathList = new ArrayList<>();
            ArrayList<String> allPaths = new ArrayList<>();
    
            // add source to path[]
            pathList.add(src);
    
            // Call recursive utility
            trips.printAllPathsUtil(src, dest, isVisited, pathList, allPaths, 0);
            
            return allPaths.toString();
        } catch (NumberFormatException nfe) {
            return "NO SUCH ROUTE";
        }
    }
    
}