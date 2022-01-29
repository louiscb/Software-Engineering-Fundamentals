import java.lang.Math;
public class Decide {
    Parameters params = new Parameters();
    public static void main(String[] args) {
        Decide decide = new Decide();
        System.out.println(decide.triangleArea(decide.new Point(0, 0), decide.new Point(1,0), decide.new Point(2,2)));
        
    }

    boolean evaluateLIC_0() {
        for (int i = 0; i < params.points.length; i++) {
            if (i + 1 == params.points.length)
                return false;
            if (params.points[i].distanceTo(params.points[i + 1]) > params.LENGTH1)
                return true;
        }
        return false;
    }

    boolean evaluateLIC_5() {
        for (int i = 0; i < params.points.length; i++) {
            if (i + 1 == params.points.length)
                return false;
            if (params.points[i+1].getX() - params.points[i].getX() < 0)
                return true;
        }
        return false;
    }

    boolean evaluateLIC_10() {
        if (params.points.length < 5) return false;
        for (int i = 0; i < params.points.length - params.E_PTS - params.F_PTS - 2; i++) {
            Point a = params.points[i];
            Point b = params.points[i + params.E_PTS + 1];
            Point c = params.points[i + params.E_PTS + params.F_PTS + 2];
            if (triangleArea(a,b,c) > params.AREA1)
                return true;
        }
        return false;
    }

    void generatePUM() { 
        for (int i = 0; i < params.PUM.length; i++) {
            for (int j = 0; j < params.PUM.length; j++) {
                if (i == j) continue; // ignore diagonal PUM elements
                params.PUM[i][j] = (params.LCM[i][j] == Parameters.Connectors.ANDD && params.CMV[i] && params.CMV[j])
                                   || (params.LCM[i][j] == Parameters.Connectors.ORR && (params.CMV[i] || params.CMV[j]))
                                   || (params.LCM[i][j] == Parameters.Connectors.NOTUSED);
            }
        }
    }

    public class Point {
        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        void printPoint() { 
            System.out.println("(" + this.x + "," + this.y + ")");
        }

        double getX() { return this.x; }
        double getY() { return this.y; }

        double distanceTo(Point b) {
            return Math.hypot(this.getX() - b.getX(), this.getY() - b.getY());
        }
    }

    double triangleArea(Point a, Point b, Point c) {
        return Math.abs(0.5*(a.getX()* (b.getY()-c.getY()) + 
                            b.getX()* (c.getY() - a.getY()) + 
                            c.getX()* (a.getY() - b.getY())));
    }
    
    boolean launch(boolean[] FUV) {
        for (int i=0; i<FUV.length; i++) {
            if (!FUV[i]) {
                System.out.println("NO");
                return false;
            }
        }
        System.out.println("YES");
        return true;
    }
}
