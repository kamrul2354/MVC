package ca.sheridan.shafiqmu.model;

/*
 * Midterm Exam Part 2 Section P15 Fall 2024
 * Java Class  Gym Member as business object
 * Author: Muhammad Shafique
 * Date: October 22, 2024
 */
public class Member {
	
	// properties of Gym Member object
		
		private String memberName;
		private String memberEmail;
		private String memberPhone;
				
		// no-arg constructor
		public Member() {
					
		}
				
		// Setters for the properties
		public void setMemberName(String memberName) {
			this.memberName = memberName;
		}
				
		public void setMemberEmail(String memberEmail) {
			this.memberEmail = memberEmail;
		}
				
		public void setMemberPhone(String memberPhone) {
			this.memberPhone = memberPhone;
		}
				
		// Getters for the properties
		public String getMemberName() {
			return this.memberName;
		}
					
		public String getMemberEmail() {
			return this.memberEmail;
		}
					
		public String getMemberPhone() {
			return this.memberPhone;
		}
	}
