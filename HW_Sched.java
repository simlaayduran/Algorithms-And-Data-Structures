import java.util.*;
 
// No Collaborator
// Author : 260822715
 
class Assignment implements Comparator<Assignment>{
	int number;
	int weight;
	int deadline;
	
	
	protected Assignment() {
	}
	
	protected Assignment(int number, int weight, int deadline) {
		this.number = number;
		this.weight = weight;
		this.deadline = deadline;
	}
	
	
	
	/**
	 * This method is used to sort to compare assignment objects for sorting. 
	 */
	@Override
	public int compare(Assignment a1, Assignment a2) {
		int compare = a1.deadline - a2.deadline;
        if(compare == 0)
            compare = a2.weight - a1.weight;
        
        return compare;
    }
 
}
 
public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
	int m;
	int lastDeadline = 0;
	
	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		// Creating new homework/assignment
		for (int i=0; i<size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			// If the specific assignments deadline is the last make it last in the line for the set of homeworks/assignments
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m =size;
	}
	
	
	/**
	 * 
	 * @return Array where output[i] corresponds to the assignment 
	 * that will be done at time i.
	 */
	public int[] SelectAssignments() {
        //Sort works
        //Order will depend on how compare function is implemented
        Collections.sort(Assignments, new Assignment());
        // If homeworkPlan[i] has a value -1, it indicates that the
        // i'th timeslot in the homeworkPlan is empty
        // homeworkPlan contains the work schedule between now and the last deadline
        int[] homeworkPlan = new int[lastDeadline];
        int index = 0;
        for (int i=0; i < homeworkPlan.length; ++i) {
            if(index < Assignments.size()){
                if(Assignments.get(index).deadline <= lastDeadline){
                    if(i > 0){
                        if(Assignments.get(index).deadline == Assignments.get(index-1).deadline && i < Assignments.get(index).deadline){
                           homeworkPlan[i] = Assignments.get(index).number;
                        }
                        else{
                            int flag = 0;
                            while(Assignments.get(index).deadline == Assignments.get(index-1).deadline){
                                index++;
                                if(index >= Assignments.size()){
                                    flag = 1;
                                    break;
                                }
                            }
                            if(flag == 0)
                                homeworkPlan[i] = Assignments.get(index).number;
                        }
                    }
                    else
                        homeworkPlan[i] = Assignments.get(index).number;
                }
                else
                    homeworkPlan[i] = -1;
                index++;
            }
            else
                homeworkPlan[i] = -1;
        }
        return homeworkPlan;
    }
}