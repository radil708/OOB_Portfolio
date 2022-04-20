/**
 * This class is a vector. A vector contains the following: an x component, a y component, a z
 * component, a magnitude calculated using the x,y,z.
 */

public class vector {
  private double x;
  private double y;
  private double z;
  private double magnitude;

  /**
   * This constructs a 3D vector. A vector has 3 dimensions (the 3 parameters) and a magnitude. The
   * magnitude is the absolute value of the square root of x^2 + y^2 + z^2.
   *
   * @param x represents the first dimension (x-axis) as a double.
   * @param y represent the second dimension (y-axis) as a double.
   * @param z represents the third dimension (z-axis) as a double.
   */
  public vector(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
    double term1 = Math.pow(this.x, 2);
    double term2 = Math.pow(this.y, 2);
    double term3 = Math.pow(this.z, 2);
    double magnitudeValue = Math.sqrt((term1 + term2 + term3));
    this.magnitude = Math.abs(magnitudeValue);
  }

  /**
   * This method is the "x" getter.
   * @return the first dimension of the vector as a double.
   */
  public double getX() {
    return this.x;
  }

  /**
   * This method is the "y" getter.
   * @return the second dimension of the vector as a double.
   */
  public double getY() {
    return this.y;
  }

  /**
   * This method is the "z" getter.
   * @return the third dimension of the vector as a double.
   */
  public double getZ() {
    return this.z;
  }

  /**
   * This method is the magnitude getter.
   * @return the magnitude of the vector as a double.
   */
  public double getMagnitude() {
    return this.magnitude;
  }

  /**
   * This method returns a string of (x,y,z) which represents the vector. Each value in the string
   * has been formatted to two decimal placed.
   * @return the "(x,y,z)" as a string.
   */

  public String toString() {
    String selfInfo;
    selfInfo = String.format("(%.2f,%.2f,%.2f)", this.x, this.y, this.z);
    return selfInfo;
  }

  /**
   * This method returns a new vector that is the equivalent of the current vector normalized. To
   * normalize a vector, each separate component is divided by the magnitude of the vector. An
   * illegalArgument Exception is thrown if the magnitude is equal to zero.
   * @return a new vector that is equivalent to the current vector normalized.
   */
  // throw illegal if cannot be done - like the case of 0
  public vector normalize() {
    if (this.magnitude == 0) {
      throw new IllegalArgumentException("You cannot normalize a vector with a 0 magnitude");
    } else {
      double normalizedX = this.x / this.magnitude;
      double normalizedY = this.y / this.magnitude;
      double normalizedZ = this.z / this.magnitude;
      return new vector(normalizedX, normalizedY, normalizedZ);
    }
  }

  /**
   * This method add the corresponding dimensions of of the current vector and another vector and
   * returns a new vector which represents the sum of the two vectors.
   * @param otherVector represents the vector that is being added to the current vector.
   * @return a new vector representing the sum of two vectors - the current one and otherVector.
   */
  public vector add(vector otherVector) {
    double sumX = this.x + otherVector.getX();
    double sumY = this.y + otherVector.getY();
    double sumZ = this.z + otherVector.getZ();
    return new vector(sumX, sumY, sumZ);
  }

  /**
   * This method returns a vector which represents a scalar multiplication of the current vector by
   * the constant parameter.
   * @param constant represents the scale by which we want to multiply to the current vector by.
   * @return a vector that represents represents a scalar multiplication of the current vector by
   * the constant parameter.
   */
  public vector multiply(double constant) {
    double productX = this.x * constant;
    double productY = this.y * constant;
    double productZ = this.z * constant;
    return new vector(productX, productY, productZ);
  }

  /**
   * This method calculates and returns the dot product of two vectors.
   * @param otherVector represents one of the vectors being used to calculate dot product. The other
   * vector is the current vector.
   * @return the dot product as a double.
   */
  public double dotProduct(vector otherVector) {
    double dotX = this.x * otherVector.getX();
    double dotY = this.y * otherVector.getY();
    double dotZ = this.z * otherVector.getZ();
    double dotFinal = dotX + dotY + dotZ;
    return dotFinal;
  }

  /**
   * This method calculates the angle between two vectors and throws an illegal argument exception
   * if the calculation cannot be performed. For ex. if the one of the vectors have a magnitude of
   * 0.
   * @param otherVector represents the vector that the method is using the calculate the angle
   * between the the current vector.
   * @return the angle between two vectors as a double in degrees.
   */
  public double angleBetween(vector otherVector) {
    double dividend = (new vector(this.x, this.y, this.z)).dotProduct(otherVector);
    double divisor = this.magnitude * otherVector.getMagnitude();
    if (divisor == 0) {
      throw new IllegalArgumentException("You cannot perform this operation due to the limits of" +
              "cosine calculation -> check if divisor in cos0 = dividend/divisor is 0 or if" +
              "the value in the right side of the calculation above leads to a value greater than" +
              "1 or less than -1");
    } else {
      double quotient = dividend / divisor;
      double radians = Math.acos(quotient);
      double angle = Math.toDegrees(radians);
      return angle;
    }
  }


}
