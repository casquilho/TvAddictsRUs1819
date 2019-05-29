package MyExceptionsPackage;

public class DuplicateCharRelated extends Exception{

        private static String message = "Like... you know, they are THE SAME character! duuuuh...";

        public DuplicateCharRelated(){
            super(message);
        }
}

