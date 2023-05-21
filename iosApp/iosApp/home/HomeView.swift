//
//  HomeView.swift
//  iosApp
//
//  Created by mac on 21.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

import SwiftUI

struct HomeView: View {

	@StateObject var homeViewModel = HomeViewModel()

        var body: some View {
//             let state = homeViewModel.state
            ScrollView {
//                 if state.isLoading {
//                     ProgressView()
//                         .frame(maxWidth: .infinity, maxHeight: .infinity)
//                 } else if state.isError {
//                     Text("Что-то пошло не так")
//                         .foregroundColor(.white)
//                         .font(.title)
//                         .fontWeight(.bold)
//                         .padding()
//                         .frame(maxWidth: .infinity, maxHeight: .infinity)
//                 } else  {
//                     VStack(spacing: 24) {
//                         Text("Выберите тему")
//                             .foregroundColor(.white)
//                             .font(.title)
//                             .fontWeight(.bold)
//
//                        List(state.items, id: \.self) { item in
//                             ListItem(item: item) {_ in
//                             }
//                             Spacer().frame(height: 8)
//                         }
//                     }
//                     .padding()
//                 }
//             }
// //             .background(Color(0xFF161A23))
//             .edgesIgnoringSafeArea(.all)
        }

//         struct ListItem: View {
//             let item: String
//             let onClick: (String) -> Void
//
//             var body: some View {
//                 Button(action: {
//                     onClick(item)
//                 }) {
//                     HStack(spacing: 16) {
//                         Text("1.")
//                             .foregroundColor(.white)
//                             .font(.system(size: 24, weight: .bold))
//                             .frame(width: 32)
//
//                         Text(item)
//                             .foregroundColor(.white)
//                             .font(.system(size: 20))
//
//                         Spacer()
//                     }
//                     .padding(16)
//                     .frame(maxWidth: .infinity)
//                     .background(Color.black)
//                     .cornerRadius(16)
//                     .shadow(color: Color.gray.opacity(0.5), radius: 8, x: 0, y: 4)
//                 }
//                 .buttonStyle(PlainButtonStyle())
//             }
//         }

}}