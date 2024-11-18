import java.util.*;
import java.io.*;

class CS2004EightQueens  {
	
	public static String init (int iterations)  {
		
		//Generate a random initial solution...
		String currentSolution = Queens.Q4_initialStart(); //initialise a string variable that save the currentSolution/first random binary string
		Double currentFitness = Queens.Q5_fitnessFunction(currentSolution); //assign the currentFitness of the current solution to the first random binary string
		int iterationCount = 0; //initialise int variable to 0 to function as counter
		
		//System.out.println("Starting solution: ");
		
		while(currentFitness != 56 && iterationCount <= iterations) {
			//Generate a new candidate solution by applying the small change operator...
			String candidateSolution = Queens.Q6_smallChange(currentSolution);
			Double candidateFitness = Queens.Q5_fitnessFunction(candidateSolution);
			
			if(candidateFitness >= currentFitness) { //if the candidate solution has better fitness, use it as the new current solution
				
				currentSolution = candidateSolution; //make the current solution equal to the new solution so solutions can keep iterating
				currentFitness = candidateFitness; //update the current fitness to the new fitness...
			}
			
			iterationCount++; //increment iterationCount by 1...
		}
		
		//if statement for testing...
		if(currentFitness == 56) { //if the current fitness is 56/optimal...
			
			//System.out.println("Solution found after " + iterationCount + " iterations.");
			//System.out.println(currentSolution);
			
		}
		else { //if not, then...
			
			//System.out.println("Failed to find a better solution after " + iterationCount + " iterations.");
		}
		return currentSolution; //return the solution with optimal fitness found within an acceptable number of iterations...
	}
}
