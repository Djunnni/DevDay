import React, { useState, useRef, useEffect } from 'react';

import { Button } from '@/components/Button';
import { InputText } from '@/components/InputText';
import { ReturnArrow } from '@/components/ReturnArrow';

import style from './inquiry.module.scss';
const idInquiry = () => {
  const [show, setShow] = useState(false);
  const bottomSheetRef = useRef(null);

  const showModal = () => {
    setShow(prev => !prev);
  };
  return (
    <div>
      <div className="div-header sticky top-0">
        <ReturnArrow title="아이디 찾기" />
      </div>
      <div className="div-body p-6">
        <InputText labelName="이름" content="홍길동" />
        <InputText labelName="닉네임" content="홍홍홍홍~" />

        <Button
          className="inquiry-bt mt-6"
          color="primary"
          fill
          label="아이디 찾기"
          onClick={showModal}
        />
      </div>

      <div
        className={style[`bottom-sheet${show ? '.show' : ''}`]}
        ref={bottomSheetRef}
      >
        <div className="bottom-sheet-content">
          <p>Hello, I am your bottom sheet content.</p>
        </div>
      </div>
    </div>
  );
};

export default idInquiry;
