<div align="center">
<img width="700" alt="Screenshot 2024-11-26 at 2 38 56 PM" src="https://github.com/user-attachments/assets/085bd7af-020d-424c-8fc7-560dc84b2fca">
</div>

# Team Members and Usernames
 Haroon Shah: haroonshah2003\
 Arhan Rupani: arhanuoft\
 Sara Elashry: saraelashry\
 Lauren McCaughey: laurenjmcc

# Table of Contents
1. [Project Summary](#project-summary)
2. [User Stories](#user-stories)
3. [Features of Software](#features-of-software)
4. [APIs](#apis)
5. [Installation Instructions](#installation-instructions)
6. [License](#license)
7. [Feedback](#feedback)

# Project Summary
  Our project is a Java Application with a graphical user interface that allows users to input protein sequences, and interact with the Proteins API (cited below) to give you a description of the protein, and predict mutations in the given sequence. Our program allows for users to sign up and log in, as well as view past results. You can also create a team with multiple users, so that when one person on your team analyzes a protein, every user in the team can see those results. 

# User stories
1. Fatima wants to create an account and use our software. First, she runs the program, now in the Login View. Next, she clicks the “Create account” button taking her to the Sign up View. She will input a string username and password in the corresponding text box input places. If the username or password already exists it will raise an error then she will be prompted to input a new username and password. Otherwise, the account will be created. Now, she is prompted back to the Login View and has to login with the account she created and now she ready to begin to use of program. (Sara's user story) 
2. Alice is a biologist who wants to study the effect of a specific mutation on a protein. She runs the program on her computer and enters the protein sequence in the “Protein Sequence” input text area it’s confirmed that the protein exists, if not it will raise an error. She clicks a button on the interface to analyze the data, and the description of the protein sequence she entered will appear.(Arhan's user story)
3. Bob has used our software before, and wants to access his past results. He logs in, then he clicks a button for past results. It gives Bob a pop-up window with all of the past results he has received on that account.(Haroon's user story)
4. Tim and Tom are working on biological research together, and want to have access to the same results. Tom creates a team by clicking create team and inputting both Tim and Tom's username. Raises an error if the similar team name already exist.(Lauren's user story)
5. Tim runs the program on his computer and enters the protein sequence in the “Protein Sequence” input text area.  After reading the description he felt like he wanted to learn more about the results of his inputs. To do that, he then goes back to the Logged in view and by pressing the “Structure” button and inputting the PBD ID, Tim can now view the struture of the protein. Now Tim has a clear and thorough understanding of the protein sequence he entered.(Team Use Case)

# Features of Software
- The "Main.java" file is the file that executes the program.
- In the program, you can create an account, and create a team. User and team data will be automatically saved, even when the program is terminated.
- You may input a protein name, and click "analyze", which will give you the option to see both a description of the protein, as well as a list of diseases pertaining to the given protein.
- You can view your past analysis results by clicking "Past Results". Past results also shows the results for anyone in the same team as you, making for easy collaboration.
- You may click "Structure" and input a PDB ID (a unique, four-character code that identifies each entry in the Protein Data Bank (PDB)), and generate a visual model of the given biological molecule in a pop out window.
- The structure model has customization features, like changing the style or colour of the model.
- You can see a visual representation of the user database in "user.json".
- You can see a visual representation of the team database in "teams.json".
- You can see a visual representation of the accumulated past results database in "analysis_result.json".

# APIs
- Proteins API: [https://www.ebi.ac.uk/proteins/api/doc/](https://www.ebi.ac.uk/proteins/api/doc/) 
- BioJava API: [https://biojava.org/docs/api/](https://biojava.org/docs/api/)

# Installation Instructions
- Click "Code" at the top of the respository, copy the provided link, and open it in your provided Java Software.
- Create a Maven project by right clicking the pom.xml file and clicking "Add as Maven Project"
- To confirm this was done correctly, open the external libraries, and make sure you see libraries for all dependencies in pom.xml, specifically "json", "okhttp", "junit", and "biojava"
- Navigate to src -> main -> java -> app -> Main
- Now, to run the program, all you have to do is run the Main.java file!

# Usage Guide
# Step 1: Click on the "Create account" button to take you to the sign up screen:
<img width="1434" alt="Screen Shot 2024-12-02 at 4 11 01 PM" src="https://github.com/user-attachments/assets/aaf16cd6-9e88-4381-b6b4-a729d143f35c">

# Step 2: Enter your username and password in the login screen:
(make sure to enter the correct username and password)
<img width="1432" alt="Screen Shot 2024-12-02 at 4 12 15 PM" src="https://github.com/user-attachments/assets/1049d6be-7b7f-489f-b34a-0ce2fe6d3b36">

# Step 3: Enter your desired protein name

<img width="1433" alt="Screen Shot 2024-12-02 at 4 13 52 PM" src="https://github.com/user-attachments/assets/7b182f6f-5e98-4419-90d3-25efb363338e">

# Step 4: Press on "Anaylze" button to see the list of diseases and description of the protein you have entered

<img width="1443" alt="Screen Shot 2024-12-02 at 4 14 53 PM" src="https://github.com/user-attachments/assets/37918075-7ae6-4631-b3d7-2aad6c0a24cd">

# Step 5: To view the structure of the protein go back to the logged in screen and click on the "Structure" button:

<img width="1441" alt="Screen Shot 2024-12-02 at 4 15 47 PM" src="https://github.com/user-attachments/assets/39d5101d-ee5b-4551-8285-d083ab6e5659">
<img width="1440" alt="Screen Shot 2024-12-02 at 4 15 58 PM" src="https://github.com/user-attachments/assets/1e6943b1-0e09-4c4f-9f75-154a7f16209d">

# Step 6: Once you are in the structure screen,enter the PDB ID of your desired protein to view it 

<img width="1429" alt="Screen Shot 2024-12-02 at 4 16 57 PM" src="https://github.com/user-attachments/assets/410dfe12-a5b6-4556-ade5-d97844ece93d">
<img width="1431" alt="Screen Shot 2024-12-02 at 4 17 12 PM" src="https://github.com/user-attachments/assets/c43d3c51-fa21-486a-80d4-a365928e95c6">


# Step 7: To create a step go back to the logged in screen and click on the  "Create Team" button and Create your team:
(make sure your team members are sepearted by commas, and have created an account or else will raise an error)
<img width="1441" alt="Screen Shot 2024-12-02 at 4 15 47 PM" src="https://github.com/user-attachments/assets/39d5101d-ee5b-4551-8285-d083ab6e5659">
<img width="1437" alt="Screen Shot 2024-12-02 at 4 24 41 PM" src="https://github.com/user-attachments/assets/a1193fd5-8b7d-4347-b1ad-10dd91d72945">

# Step 8: To look at your or your team mates past results, simply go back to the logged in screen and click on the "Past Results" button:
<img width="1441" alt="Screen Shot 2024-12-02 at 4 15 47 PM" src="https://github.com/user-attachments/assets/39d5101d-ee5b-4551-8285-d083ab6e5659">
<img width="1429" alt="Screen Shot 2024-12-02 at 4 26 16 PM" src="https://github.com/user-attachments/assets/ce3f392c-f79b-4057-890e-5e6ce361ef2c">

# License
![Capture](https://github.com/user-attachments/assets/1c9588bf-8429-4708-a62f-b9a3317227ed)
- For complete licence, refer to the "LICENSE" text file in our repository.

# Feedback
If you have any comments, questions, or concerns about our project, please fill out the following google form:
https://forms.gle/n477QyBT83HGPx5E9

#Contributions

