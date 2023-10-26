import React from 'react';
import styled from 'styled-components';

interface ActionButtonProps {
  onClick?: React.MouseEventHandler<HTMLDivElement>;
  optionText?: string;
  btnColor?: string;
}

const ButtonDiv = styled.div<ActionButtonProps>`
  display: flex;
  width: 300px;
  height: 60px;
  margin: 10px;
  font-family: 'CookieRun';
  font-size: 40px;
  font-weight: 900;
  border-radius: 20px;
  box-shadow: 2px 2px 7px rgba(0, 0, 0, 0.5);
  background-color: ${(props) => props.btnColor};
  justify-content: center;
  align-items: center;
`;

function ActionButton({ onClick, optionText, btnColor }: ActionButtonProps) {
  return (
    <ButtonDiv onClick={onClick} btnColor={btnColor}>
      {optionText}
    </ButtonDiv>
  );
}

ActionButton.defaultProps = {
  optionText: '',
  onClick: console.log('test'),
  btnColor: 'white',
};

export default ActionButton;
