package com.example.szaboz.sonrisalibrary.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.szaboz.sonrisalibrary.R;
import com.example.szaboz.sonrisalibrary.adapter.ManageBookAdapter;
import com.example.szaboz.sonrisalibrary.bean.Book;
import com.example.szaboz.sonrisalibrary.factory.DemoBooksFactory;

import java.util.ArrayList;

import static java.util.stream.Collectors.toCollection;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ManageBooksFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ManageBooksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageBooksFragment extends Fragment {
    public static ManageBooksFragment newInstance() {
        return new ManageBooksFragment();
    }
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView listView;
    private OnFragmentInteractionListener mListener;

    public ManageBooksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ManageBooksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ManageBooksFragment newInstance(String param1, String param2) {
        ManageBooksFragment fragment = new ManageBooksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        getActivity().setTitle(R.string.manage_books);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manage_books, container, false);
        listView = (ListView) view.findViewById(R.id.manage_books_listView);
        DemoBooksFactory booksFactory = new DemoBooksFactory();

        ArrayList<Book> borrowedBooks = booksFactory.getDemoBooksData().
                stream().
                filter(book -> "szaboz".equals(book.getBorrower())). //TODO: actual user email identifier instead of "szaboz"
                collect(toCollection(ArrayList::new));

        ManageBookAdapter manageBookAdapter = new ManageBookAdapter(getActivity(), inflater, borrowedBooks);
        listView.setAdapter(manageBookAdapter);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
