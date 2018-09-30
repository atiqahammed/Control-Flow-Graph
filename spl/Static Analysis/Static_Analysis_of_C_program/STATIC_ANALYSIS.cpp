/*
    # Written by A.N.TANVIR on 18 January,2018 (starting).

    # Tried to implement "Static Analysis" for finding out
    the variables used in the input C code.

    >>Done by an amateur coder!
*/


#include<bits/stdc++.h>
using namespace std;

bool comma=false; //to check whether several variables are declared using comma operator.
map<string,int> varCount;
map<string,vector<string> > varName;

//==>
void printVar();
bool process(string &name);


int main (){

    //freopen("input.txt","r",stdin);
    //freopen("output.txt","w",stdout);

    string var[]={"int","char","double","float","bool"};
    string s;

    while(getline(cin,s)){
        stringstream ss(s);
        string word,name;
        string preVar;             //to store previous variable type.
        bool flfunc=false,unnecessary;

        while(ss>>word){

            if(flfunc && word[0]=='('){        // int main ()."main" should not be included.
                varName[preVar].pop_back();
                varCount[preVar]--;
            }

            if(comma){
                unnecessary=process(word);
                varName[preVar].push_back(word);
                varCount[preVar]++;
            }

            if(word==","){ comma=true; }
            if(word==";"){ comma=false; }

            if(word.size()>1 && word[0]==','){                     // int ab ",ac" ",ad" ;
                unnecessary=process(word);
                varName[preVar].push_back(word);
                varCount[preVar]++;
            }

            if(word[0]==';'||word[0]=='('){                   // float a ";char" ch ;
                unnecessary=process(word);                    // float function "(int" value){...}
            }


            flfunc=false;
            for(int i=0; i<sizeof(var)/sizeof(var[0]) ; i++){
                if(word==var[i]){
                    ss>>name;

                    preVar=word;
                    flfunc=true;       //to check whether "name" is a function name or variable.
                                       // at this moment it is unknown.
                    bool flag = process(name);
                    /*
                    if(flag==false){
                        varName[word].pop_back();
                    }
                    */
                    if(flag==true){
                        varName[word].push_back(name);
                        varCount[word]++;
                        //cout<<varName[word][0]<<endl<<varCount[word]<<endl;
                    }
                }
            }
        }
    }
    printVar();

    return 0;
}


//==>
bool process(string &name){
    //cout<<name<<"\t";
    int l=name.size();

    if(name[l-1]=='('||name[l-1]=='{')return false;
    if(name[l-1]==')'){
        name.erase(name.end()-1);
        //cout<<name<<"\n";
        return true;
    }
    if(name[l-1]=='}'){
         name.erase(name.end()-2,name.end()-1);
    }

    if(name[0]==';'||name[0]==','||name[0]=='('){
        name.erase(name.begin());

        int nl=name.size();     //new length after erasing.

        if(name[nl-1]==','){
            if(name[nl-1]==',')comma=true;

            name.erase(name.end()-1);
            return true;
        }
    }

    if(name[l-1]==';'||name[l-1]==','){
        if(name[l-1]==',')comma=true;
        if(name[l-1]==';')comma=false;
        name.erase(name.end()-1);
        //cout<<name<<"\n";
        return true;
    }
    return true;
}


//==>
void printVar(){
    cout<<"\nNumber of variables in the given C code:\n";

    map<string, int> :: iterator it=varCount.begin();
    for( ;it!=varCount.end(); it++){
        cout<<(*it).first<<"\t"<<(*it).second<<endl;
    }

    cout<<"\nName of variables:\n\n";
    map<string, vector<string> > :: iterator itr=varName.begin();
    for( ;itr!=varName.end(); itr++){
        string type=itr->first;
        cout<<type<<"\t";
        for(int i=0; i<varName[type].size(); i++){
            cout<<varName[type][i]<<" ";
        }
        cout<<endl;
    }

}
