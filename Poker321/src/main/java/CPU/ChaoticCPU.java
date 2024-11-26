package CPU;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author giosa
 */

import java.util.Random;

/**
 * this class represents a Chaotic CPU player that makes random betting and folding decisions.
 */

public class ChaoticCPU extends CPU 
{
    // generate random numbers
    private Random random;

    // constructor
    public ChaoticCPU(String name, int chips) 
    {
     super(name,chips);   
    }

    // method to make move randomly
    public void MakeMove() 
    {
       
    }

    // method to fold randomly 
    public void Fold() 
    {
        
    }
}
