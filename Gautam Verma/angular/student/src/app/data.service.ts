import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  domainName: string = "mydemo.com";
  userNames: string[] = ["user1", "user2", "user3", "user4"];
  subjects = {
    '101': "JAVA",
    '102': "Angular",
    '103': "Python",
    '104': "C++",
    '105': "Ruby",
    '106': "JavaScript",
    '107': "CSS",
    '108': "Node",
  }

  getSubjects(): Object {
    return this.subjects;
  }
  constructor() { }
}
