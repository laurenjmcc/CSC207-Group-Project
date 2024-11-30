<img width="700" alt="Screenshot 2024-11-26 at 2 38 56 PM" src="https://github.com/user-attachments/assets/085bd7af-020d-424c-8fc7-560dc84b2fca">

# Team Members and Usernames:
 Haroon Shah: haroonshah2003\
 Arhan Rupani: arhanuoft\
 Sara Elashry: saraelashry\
 Lauren McCaughey: laurenjmcc

# Project Summary
  Our project is a Java Application with a graphical user interface that allows users to input protein sequences, and interact with the Proteins API (cited below) to give you a description of the protein, and predict mutations in the given sequence. Our program allows for users to sign up and log in, as well as view past results. You can also create a team with multiple users, so that when one person on your team analyzes a protein, every user in the team can see those results. 

# User stories:
1. Fatima wants to create an account and use our software. First, she runs the program. Next, she clicks the “Create account” button. She will input a string username and password in the corresponding text box input places. If  the username or password already exists it will raise an error then she will be prompted to input a new username and password. Otherwise, the account will be created. Now, to use our software, she has to login with the account she created and begin. (Sara's user story) 
2. Alice is a biologist who wants to study the effect of a specific mutation on a protein. She runs the program on her computer and enters the protein sequence in the “Protein Sequence”input text area it’s confirmed that the protein exists, if not it will raise an error. She clicks a button on the interface to analyze the data,and the description of the protein sequence she entered will appear.(Arhan's user story)
3. Bob has used our software before, and wants to access his past results. He logs in, then he clicks a button for past results. It gives Bob a pop-up window with all of the past results he has received on that account.(Haroon's user story)
4. Tim and Tom are working on biological research together, and want to have access to the same results. Tom creates a team by clicking create team and inputting both Tim and Tom's username. Raises an error if the similar team name already exist.(Lauren's user story)
5. Tim runs the program on his computer and enters the protein sequence in the “Protein Sequence” input text area.  After reading the description he felt like he wanted to learn more about the results of his inputs. To do that,  he then goes back to the Logged in view and by pressing the “Structure” button and inputting the PBD ID, Tim can now view the struture of the protein. Now Tim has a clear and thorough understanding of the protein sequence he entered.(Team Use Case)

# APIs
- Proteins API: [https://www.ebi.ac.uk/proteins/api/doc/](https://www.ebi.ac.uk/proteins/api/doc/) 
- BioJava API: [https://biojava.org/docs/api/](https://biojava.org/docs/api/)

# Installation Instructions
- Click "Code" at the top of the respository, copy the provided link, and open it in your provided Java Software.
- Create a Maven project by right clicking the pom.xml file and clicking "Add as Maven Project"
- To confirm this was done correctly, open the external libraries, and make sure you see libraries for all dependencies in pom.xml, specifically "json", "okhttp", "junit", and "biojava"
- Navigate to src -> main -> java -> app -> Main
- Now, to run the program, all you have to do is run the Main.java file!
