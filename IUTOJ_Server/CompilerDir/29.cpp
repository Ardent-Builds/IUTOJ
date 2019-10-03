#include<bits/stdc++.h>
using namespace std;
int main()
{
    long double x, y, z;
    int t;
    cin>>t;
    for(int i=1;i<=t;i++)
    {
        cin>>x;
        y=x*sqrt(2);
//        cout<<y<<'\n';
        y=y*y;
//        cout<<y<<'\n';
        x=x*x;
        z=y-x;
//        cout<<z<<'\n';
        z=sqrt(z);
        long double res=z*z*acos(-1);
        cout<<fixed;
        cout<<res<<'\n';
    }
}
