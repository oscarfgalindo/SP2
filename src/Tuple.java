import java.awt.Point;


public class Tuple {
		public Double x;
		public Double y;

		public Tuple(Double x, Double y) {
			this.x = x;
			this.y = y;
		}
		
		public Tuple(Integer x, Integer y) {
			this.x = Double.valueOf(x);
			this.y = Double.valueOf(y);
		}

		public double getX() {
			return x;
		}

		public double getY() {
			return y;
		}
		

		public void edit(Double a, Double b) {
			x = a;
			y = b;
		}
		
		public void sum(Tuple other) {
			x += other.x;
			y += other.y;
			// this.edit(x, y);
		}
		
		public void sub(Tuple other){
			x -= other.x;
			y -= other.y;
		}

		public Tuple subtract(Tuple other) {
			return new Tuple(x-other.x, y-other.y);
		}

		public Tuple multiply(double d) {
			return new Tuple(x * d, y * d);
		}
		

		public Point toPoint() {
			return new Point(x.intValue(), y.intValue());
		}
		
		@Override
		public String toString() { 
			return String.format("(" + x + "," + y +")"); 
		} 
		
	}