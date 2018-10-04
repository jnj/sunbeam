package org.joshjoyce.sunbeam;

public class ViewPlane {

    int hres;
    int vres;
    float s;
    float gamma;
    float invGamma;

    void setGamma(float v) {
        gamma = v;
        invGamma = 1.0F / v;
    }
}
