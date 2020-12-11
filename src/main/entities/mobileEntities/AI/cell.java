package main.entities.mobileEntities.AI;

public class cell {
    int parent_i, parent_j;
    // f = g + h
    double f, g, h;

    public cell(int parent_i, int parent_j, double f, double g, double h) {
        this.parent_i = parent_i;
        this.parent_j = parent_j;
        this.f = f;
        this.g = g;
        this.h = h;
    }
}
