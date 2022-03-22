.globl loc_of_min selection_sort sort_two

.data

testArray1: .word 5 8 7 3 9 2 4 1 0 6 1234
testArray2: .word 5 8 7 7 3 5 9 4 1 0 6 0 1 5 2

.text

# Use this to test your code
main:
	la $a0, testArray1
	li $a1, 10
	jal selection_sort
	
	la $a0, testArray2
	li $a1, 15
	jal selection_sort
	
	
	li $v0, 10
	syscall
	

#
# a0 is a pointer to array1
# a1 is a pointer to the number of elements in array1
# a2 is a pointer to array2
# a3 is a pointer to the number of elements in array2
#
sort_two:	
  # IMPORTANT: The purpose of sort_two is so that I can verify that 
  # your selection_sort correctly sets up and restores the stack.
  # IT DOES NOT FOLLOW MIPS CONVENTIONS
  # I "cheated" in several places to make my tests work.
  # DON'T USE THIS CODE AS A MODEL FOR YOUR CODE.  
  move $s0, $a2
  move $s1, $a3
  move $s3, $ra  # (Don't do this)
  jal selection_sort
  
  move $a0, $s0
  move $a1, $s1
  jal selection_sort
  
  move $ra, $s3
  jr $ra
	
	

#
# a0 is a pointer to an integer array
# a1 is the number of elements in the array
#
# Return a *pointer* to the minimum value in the array.
#   (pointer, *not* an index.)
#
# Assume that the array has at least one element.
#
loc_of_min:

   # Your code here
  
   jr $ra

#
# a0 is a pointer to an integer array
# a1 is the number of elements in that array
#
#  Array length *may* be zero.
selection_sort:

  # Your code here

  jr $ra

    
  
  
  
  
  


