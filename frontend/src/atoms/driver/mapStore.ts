import { atom } from "jotai";

export const mapAtom = atom<any | null>(null);
export const routeDataAtom = atom<any | null>(null); // 필요한 경로 데이터 타입 설정
export const mapInitializedAtom = atom<boolean>(false);

export const startPointAtom = atom<{ lat: number; lon: number } | null>({
  lat: 34.75746280159556,
  lon: 126.35492245670288,
});
export const endPointAtom = atom<{ lat: number; lon: number } | null>({
  lat: 35.2054670279613,
  lon: 126.81110170278345,
}); // 도착지 좌표
export const restStopsAtom = atom<{ lat: number; lon: number; tag: number }[]>([
  { lat: 37.51148310935, lon: 127.06033711446, tag: 3 }, // 기본 IIPass 더미 데이터
]);
