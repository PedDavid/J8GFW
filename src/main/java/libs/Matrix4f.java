package libs;

import com.sun.javafx.geom.Vec3f;
import java.nio.FloatBuffer;

public class Matrix4f {

    public float
            m00, m01, m02, m03,
            m10, m11, m12, m13,
            m20, m21, m22, m23,
            m30, m31, m32, m33;

    public Matrix4f(){
        this(1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f);
    }

    public Matrix4f(float[]m){
        this(m[0], m[1], m[2], m[3],
                m[4], m[5], m[6], m[7],
                m[8], m[9], m[10], m[11],
                m[12], m[13], m[14], m[15]);
    }

    public Matrix4f(Matrix4f m){
        this(m.m00, m.m01, m.m02, m.m03,
                m.m10, m.m11, m.m12, m.m13,
                m.m20, m.m21, m.m22, m.m23,
                m.m30, m.m31, m.m32,m.m33);
    }

    public Matrix4f(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13,
                    float m20, float m21, float m22, float m23, float m30, float m31, float m32, float m33){

        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m03 = m03;

        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;

        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;

        this.m30 = m30;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
    }

    public void store(FloatBuffer buffer){
        buffer.put(m00);
        buffer.put(m01);
        buffer.put(m02);
        buffer.put(m03);
        buffer.put(m10);
        buffer.put(m11);
        buffer.put(m12);
        buffer.put(m13);
        buffer.put(m20);
        buffer.put(m21);
        buffer.put(m22);
        buffer.put(m23);
        buffer.put(m30);
        buffer.put(m31);
        buffer.put(m32);
        buffer.put(m33);
    }

    public final void set(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13,
                          float m20, float m21, float m22, float m23, float m30, float m31, float m32, float m33){
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m03 = m03;

        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;

        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;

        this.m30 = m30;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
    }

    public final void set(Matrix4f m){
        set(m.m00, m.m01, m.m02, m.m03,
                m.m10, m.m11, m.m12, m.m13,
                m.m20, m.m21, m.m22, m.m23,
                m.m30, m.m31, m.m32,m.m33);
    }

    public final void setIdentity(){
        set(1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f);
    }
    public final void setZero(){
        set(0.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 0.0f);
    }

    public final void negate(){
        set(-m00, -m01, -m02, -m03,
                -m10, -m11, -m12, -m13,
                -m20, -m21, -m22, -m23,
                -m30, -m31, -m32, -m33);
    }

    public final void add(float scalar){
        m00 += scalar;
        m01 += scalar;
        m02 += scalar;
        m03 += scalar;
        m10 += scalar;
        m11 += scalar;
        m12 += scalar;
        m13 += scalar;
        m20 += scalar;
        m21 += scalar;
        m22 += scalar;
        m23 += scalar;
        m30 += scalar;
        m31 += scalar;
        m32 += scalar;
        m33 += scalar;
    }

    public final void sub(float scalar){
        add(-scalar);
    }

    public final void add(Matrix4f m1){
        this.m00 += m1.m00;
        this.m01 += m1.m01;
        this.m02 += m1.m02;
        this.m03 += m1.m03;

        this.m10 += m1.m10;
        this.m11 += m1.m11;
        this.m12 += m1.m12;
        this.m13 += m1.m13;

        this.m20 += m1.m20;
        this.m21 += m1.m21;
        this.m22 += m1.m22;
        this.m23 += m1.m23;

        this.m30 += m1.m30;
        this.m31 += m1.m31;
        this.m32 += m1.m32;
        this.m33 += m1.m33;
    }

    public final void sub(Matrix4f m1){
        m1.negate();
        add(m1);
    }

    public final void mul(float scalar){
        m00 *= scalar;
        m01 *= scalar;
        m02 *= scalar;
        m03 *= scalar;
        m10 *= scalar;
        m11 *= scalar;
        m12 *= scalar;
        m13 *= scalar;
        m20 *= scalar;
        m21 *= scalar;
        m22 *= scalar;
        m23 *= scalar;
        m30 *= scalar;
        m31 *= scalar;
        m32 *= scalar;
        m33 *= scalar;
    }

    public final void mul(Matrix4f m1){
        float       m00, m01, m02, m03,
                m10, m11, m12, m13,
                m20, m21, m22, m23,
                m30, m31, m32, m33;  // vars for temp result matrix

        m00 = this.m00*m1.m00 + this.m01*m1.m10 +
                this.m02*m1.m20 + this.m03*m1.m30;
        m01 = this.m00*m1.m01 + this.m01*m1.m11 +
                this.m02*m1.m21 + this.m03*m1.m31;
        m02 = this.m00*m1.m02 + this.m01*m1.m12 +
                this.m02*m1.m22 + this.m03*m1.m32;
        m03 = this.m00*m1.m03 + this.m01*m1.m13 +
                this.m02*m1.m23 + this.m03*m1.m33;

        m10 = this.m10*m1.m00 + this.m11*m1.m10 +
                this.m12*m1.m20 + this.m13*m1.m30;
        m11 = this.m10*m1.m01 + this.m11*m1.m11 +
                this.m12*m1.m21 + this.m13*m1.m31;
        m12 = this.m10*m1.m02 + this.m11*m1.m12 +
                this.m12*m1.m22 + this.m13*m1.m32;
        m13 = this.m10*m1.m03 + this.m11*m1.m13 +
                this.m12*m1.m23 + this.m13*m1.m33;

        m20 = this.m20*m1.m00 + this.m21*m1.m10 +
                this.m22*m1.m20 + this.m23*m1.m30;
        m21 = this.m20*m1.m01 + this.m21*m1.m11 +
                this.m22*m1.m21 + this.m23*m1.m31;
        m22 = this.m20*m1.m02 + this.m21*m1.m12 +
                this.m22*m1.m22 + this.m23*m1.m32;
        m23 = this.m20*m1.m03 + this.m21*m1.m13 +
                this.m22*m1.m23 + this.m23*m1.m33;

        m30 = this.m30*m1.m00 + this.m31*m1.m10 +
                this.m32*m1.m20 + this.m33*m1.m30;
        m31 = this.m30*m1.m01 + this.m31*m1.m11 +
                this.m32*m1.m21 + this.m33*m1.m31;
        m32 = this.m30*m1.m02 + this.m31*m1.m12 +
                this.m32*m1.m22 + this.m33*m1.m32;
        m33 = this.m30*m1.m03 + this.m31*m1.m13 +
                this.m32*m1.m23 + this.m33*m1.m33;

        this.m00 = m00; this.m01 = m01; this.m02 = m02; this.m03 = m03;
        this.m10 = m10; this.m11 = m11; this.m12 = m12; this.m13 = m13;
        this.m20 = m20; this.m21 = m21; this.m22 = m22; this.m23 = m23;
        this.m30 = m30; this.m31 = m31; this.m32 = m32; this.m33 = m33;
    }

    public final void mult(Vec3f scalar){
        m00 *= scalar.x;
        m01 *= scalar.x;
        m02 *= scalar.x;
        m03 *= scalar.x;
        m10 *= scalar.y;
        m11 *= scalar.y;
        m12 *= scalar.y;
        m13 *= scalar.y;
        m20 *= scalar.z;
        m21 *= scalar.z;
        m22 *= scalar.z;
        m23 *= scalar.z;
    }

    public final float determinant(){
        float det;

        det = m00*(m11*m22*m33+ m12*m23*m31 + m13*m21*m32
                - m13*m22*m31 -m11*m23*m32 - m12*m21*m33);
        det -= m01*(m10*m22*m33+ m12*m23*m30 + m13*m20*m32
                - m13*m22*m30 -m10*m23*m32 - m12*m20*m33);
        det += m02*(m10*m21*m33+ m11*m23*m30 + m13*m20*m31
                - m13*m21*m30 -m10*m23*m31 - m11*m20*m33);
        det -= m03*(m10*m21*m32+ m11*m22*m30 + m12*m20*m31
                - m12*m21*m30 -m10*m22*m31 - m11*m20*m32);

        return( det );
    }

    public final void setTranslation(Vec3f trans){
        m03 = trans.x;
        m13 = trans.y;
        m23 = trans.z;
    }

    public final void translate(Vec3f vec){
        translate(vec, this, this);
    }

    public final Matrix4f translate(Vec3f vec, Matrix4f src, Matrix4f dest) {
    	if (dest == null)
            dest = new Matrix4f();

        dest.m30 += src.m00 * vec.x + src.m10 * vec.y + src.m20 * vec.z;
    	dest.m31 += src.m01 * vec.x + src.m11 * vec.y + src.m21 * vec.z;
       	dest.m32 += src.m02 * vec.x + src.m12 * vec.y + src.m22 * vec.z;
        dest.m33 += src.m03 * vec.x + src.m13 * vec.y + src.m23 * vec.z;

      	return dest;
    }

    public final void rotate(float angle, Vec3f axis){
        rotate(angle, axis, this, this);
    }

    public final Matrix4f rotate(float angle, Vec3f axis, Matrix4f src, Matrix4f dest){
        float c = (float) Math.cos(angle);
        float s = (float) Math.sin(angle);
        float oneminusc = 1.0f - c;
        float xy = axis.x*axis.y;
        float yz = axis.y*axis.z;
        float xz = axis.x*axis.z;
        float xs = axis.x*s;
        float ys = axis.y*s;
        float zs = axis.z*s;

        float f00 = axis.x*axis.x*oneminusc+c;
        float f01 = xy*oneminusc+zs;
        float f02 = xz*oneminusc-ys;
            // n[3] not used
        float f10 = xy*oneminusc-zs;
        float f11 = axis.y*axis.y*oneminusc+c;
        float f12 = yz*oneminusc+xs;
        	// n[7] not used
        float f20 = xz*oneminusc+ys;
        float f21 = yz*oneminusc-xs;
        float f22 = axis.z*axis.z*oneminusc+c;

       	float t00 = src.m00 * f00 + src.m10 * f01 + src.m20 * f02;
       	float t01 = src.m01 * f00 + src.m11 * f01 + src.m21 * f02;
       	float t02 = src.m02 * f00 + src.m12 * f01 + src.m22 * f02;
       	float t03 = src.m03 * f00 + src.m13 * f01 + src.m23 * f02;
       	float t10 = src.m00 * f10 + src.m10 * f11 + src.m20 * f12;
       	float t11 = src.m01 * f10 + src.m11 * f11 + src.m21 * f12;
       	float t12 = src.m02 * f10 + src.m12 * f11 + src.m22 * f12;
       	float t13 = src.m03 * f10 + src.m13 * f11 + src.m23 * f12;
      	dest.m20 = src.m00 * f20 + src.m10 * f21 + src.m20 * f22;
       	dest.m21 = src.m01 * f20 + src.m11 * f21 + src.m21 * f22;
      	dest.m22 = src.m02 * f20 + src.m12 * f21 + src.m22 * f22;
       	dest.m23 = src.m03 * f20 + src.m13 * f21 + src.m23 * f22;
      	dest.m00 = t00;
        dest.m01 = t01;
        dest.m02 = t02;
       	dest.m03 = t03;
       	dest.m10 = t10;
        dest.m11 = t11;
        dest.m12 = t12;
      	dest.m13 = t13;
        return dest;
    }

    @Override
    public String toString() {
        return
                this.m00 + ", " + this.m01 + ", " + this.m02 + ", " + this.m03 + "\n" +
                this.m10 + ", " + this.m11 + ", " + this.m12 + ", " + this.m13 + "\n" +
                this.m20 + ", " + this.m21 + ", " + this.m22 + ", " + this.m23 + "\n" +
                this.m30 + ", " + this.m31 + ", " + this.m32 + ", " + this.m33 + "\n";
    }

    public float getM00() {
        return m00;
    }

    public void setM00(float m00) {
        this.m00 = m00;
    }

    public float getM01() {
        return m01;
    }

    public void setM01(float m01) {
        this.m01 = m01;
    }

    public float getM02() {
        return m02;
    }

    public void setM02(float m02) {
        this.m02 = m02;
    }

    public float getM03() {
        return m03;
    }

    public void setM03(float m03) {
        this.m03 = m03;
    }

    public float getM10() {
        return m10;
    }

    public void setM10(float m10) {
        this.m10 = m10;
    }

    public float getM11() {
        return m11;
    }

    public void setM11(float m11) {
        this.m11 = m11;
    }

    public float getM12() {
        return m12;
    }

    public void setM12(float m12) {
        this.m12 = m12;
    }

    public float getM13() {
        return m13;
    }

    public void setM13(float m13) {
        this.m13 = m13;
    }

    public float getM20() {
        return m20;
    }

    public void setM20(float m20) {
        this.m20 = m20;
    }

    public float getM21() {
        return m21;
    }

    public void setM21(float m21) {
        this.m21 = m21;
    }

    public float getM22() {
        return m22;
    }

    public void setM22(float m22) {
        this.m22 = m22;
    }

    public float getM23() {
        return m23;
    }

    public void setM23(float m23) {
        this.m23 = m23;
    }

    public float getM30() {
        return m30;
    }

    public void setM30(float m30) {
        this.m30 = m30;
    }

    public float getM31() {
        return m31;
    }

    public void setM31(float m31) {
        this.m31 = m31;
    }

    public float getM32() {
        return m32;
    }

    public void setM32(float m32) {
        this.m32 = m32;
    }

    public float getM33() {
        return m33;
    }

    public void setM33(float m33) {
        this.m33 = m33;
    }
}
