module demo {
    interface Subscriber{
        void onUpdate(string s);
    }

    interface Publisher{
        void addSubscriber(string name, Subscriber* o);
        void removeSubscriber(string name);
    }
}