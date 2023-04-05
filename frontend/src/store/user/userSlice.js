import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import { PURGE } from 'redux-persist';

import http from '@/api/http';
import { LOGIN_URL } from '@/constants';

export const loginAsync = createAsyncThunk(
  'user/loginAsync',
  async ({ email, password }) => {
    const response = await http.post(LOGIN_URL, {
      email,
      password,
    });

    localStorage.setItem(
      'accessToken',
      `Bearer ${response.data.data.accessToken}`,
    );
    localStorage.setItem(
      'refreshToken',
      `Bearer ${response.data.data.refreshToken}`,
    );
    return [
      { userInfo: response.data.data.userInfo },
      { accessToken: `Bearer ${response.data.data.accessToken}` },
      { refreshToken: `Bearer ${response.data.data.refreshToken}` },
    ];
  },
);
const initialState = {
  userInfo: {},
  accessToken: '',
  refreshToken: '',
};

export const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {
    addExtraId: (state, action) => {
      // eslint-disable-next-line no-param-reassign
      state.userInfo = {
        github: action.payload[0],
        baekjoon: action.payload[1],
      };
    },
    reset(state) {
      localStorage.removeItem('accessToken');
      localStorage.removeItem('refreshToken');
      Object.assign(state, {
        userInfo: {},
        accessToken: '',
        refreshToken: '',
      });
    },
  },
  extraReducers: builder => {
    builder
      .addCase(loginAsync.pending, state => {
        return { ...state, status: 'Loading' };
      })
      .addCase(loginAsync.fulfilled, (state, action) => {
        const [userInfo, accessToken, refreshToken] = action.payload;
        return {
          ...state,
          userInfo: userInfo.userInfo,
          accessToken: accessToken.accessToken,
          refreshToken: refreshToken.refreshToken,
          status: 'Success',
        };
      })
      .addCase(loginAsync.rejected, state => {
        return { ...state, token: null, status: 'Fail' };
      })
      .addCase(PURGE, () => initialState);
  },
});
export const { reset, addGithubId, addSolvedAcId, addExtraId } =
  userSlice.actions;
export default userSlice.reducer;
